package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnAliasModel;
import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnModel;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsTable;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisSemanticColumns;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerDatasourceModel;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerDatasourceModelRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.StrategizerQueryConversionRepository;
import com.lti.recast.recastBoTableau.strategizer.util.ModelBuilder;
import com.lti.recast.recastBoTableau.strategizer.util.StrategizerUtility;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;
import net.sf.jsqlparser.util.TablesNamesFinder;

@Service
public class StrategizerQueryService {

	@Autowired(required = false)
	private StrategizerQueryConversionRepository strategizerQueryConversionRepository;

	@Autowired(required = false)
	private StrategizerDatasourceModelRepository strategizerDatasourceModelRepository;

	public List<QueryColumnAliasModel> saveQueryData(List<AnalysisReport> lstAnalysisReport, int strateTaskId,
			List<AnalysisSemanticColumns> semanticColumns, List<AnalysisReportsTable> analysisReportTableLst)
			throws JSQLParserException {
		JSONArray queryStatements = new JSONArray();
		List<StrategizerQueryConversion> strategizerQueryModelList = new ArrayList<StrategizerQueryConversion>();
		List<QueryColumnAliasModel> LstOfQueryColumnAlias = new ArrayList<QueryColumnAliasModel>();
		String databaseType = "";
		String hostName = "";
		String databaseName = "";
		if (!lstAnalysisReport.isEmpty()) {
			for (AnalysisReport anaReportList : lstAnalysisReport) {

				StrategizerQueryConversion strategizerQueryModel = new StrategizerQueryConversion();

				String queryList = anaReportList.getQueryList();

				JSONArray queryJSONArray = new JSONArray(queryList);

				for (Object y : queryJSONArray) {
					JSONObject queryListJson = (JSONObject) y;

					databaseType = "msaccess";
					hostName = "localhost";
					databaseName = queryListJson.getString("dataSourceName");
					queryStatements = queryListJson.getJSONArray("queryStatements");
					List<List<String>> statementUpdatedList = new LinkedList<List<String>>();
					List<QueryColumnModel> queryColumnList = new ArrayList<QueryColumnModel>();

					JSONArray queryColumnsJSONArray = queryListJson.getJSONArray("boReportObject");

					for (int k = 0; k < queryColumnsJSONArray.length(); k++) {
						JSONObject querycolumnJSONObj = queryColumnsJSONArray.getJSONObject(k);

						// System.out.println("Query column JSON
						// Object::"+querycolumnJSONObj.toString());

						if (!querycolumnJSONObj.isNull("dataSourceObjectId")) {
							QueryColumnModel queryColumnModelObj = ModelBuilder
									.queryColumnModelBuilder(querycolumnJSONObj);

							semanticColumns.forEach(x -> {

								if (x.getObjectIdentifier().equalsIgnoreCase(queryColumnModelObj.getObjectId())) {
									// System.out.println("Matched::"+x.getColumnNames()+"
									// functions::"+x.getFunctions());
									queryColumnModelObj.setAliasName(x.getColumnNames());

									// String functionName =
									// StrategizerUtility.extractFunctionName(x.getFunctions());

									String functionName = StrategizerUtility
											.checkTableAliasFunctionName(x.getFunctions(), analysisReportTableLst);

									// System.out.println("Function Name::"+functionName);
									queryColumnModelObj.setExpression(functionName);
									queryColumnList.add(queryColumnModelObj);

								}

							});

						}
					}
					for (Object z : queryStatements) {
						Map<String, Alias> columnAliasMap = new LinkedHashMap<String, Alias>();
						List<String> selectColumnList = StrategizerUtility.parseQuery(z.toString());
						selectColumnList.forEach(x -> {
							QueryColumnAliasModel queryColumnAlias = new QueryColumnAliasModel();
							String aliasName = fetchAliasName(x, queryColumnList);
							System.out.println("Column name:" + x + " Alias Name: " + aliasName);
							queryColumnAlias.setColumnName(aliasName != null ? aliasName : "");
							queryColumnAlias.setAliasName(x.substring(x.indexOf(".") + 1).contains(")")
									? x.substring(x.indexOf(".") + 1).replace(")", "")
									: x.substring(x.indexOf(".") + 1));

							aliasName = "'" + aliasName + "'";

							Alias aliasNameModified = new Alias(aliasName);

							columnAliasMap.put(x, aliasNameModified);
							LstOfQueryColumnAlias.add(queryColumnAlias);
						});

						List<String> queryListData = new LinkedList<String>();

						queryListData.add(z.toString());
						String queryUpdated = modifyQuerySelectItem(z.toString(), columnAliasMap);
						// System.out.println("Final Query::"+queryUpdated);
						queryListData.add(queryUpdated);

						statementUpdatedList.add(queryListData);
					}

					for (Object z : queryStatements) {
						strategizerQueryModel.setStratTaskId(strateTaskId);

						strategizerQueryModel
								.setReportId(anaReportList.getReportId() != null ? anaReportList.getReportId() : "");
						strategizerQueryModel.setReportName(
								anaReportList.getReportName() != null ? anaReportList.getReportName() : "");
						strategizerQueryModel.setDatabaseType(databaseType);
						strategizerQueryModel.setDatabaseName(databaseName.toLowerCase());
						strategizerQueryModel.setHostname(hostName);
						//strategizerQueryModel.setQueryName("");
						//strategizerQueryModel.setConvertedQueryName("");
						strategizerQueryModel.setQueryStatement("");
						strategizerQueryModel.setConvertedQueryStatement("");
						 strategizerQueryModel.setQueryName(queryListJson.getString("queryName"));
						 strategizerQueryModel.setConvertedQueryName(queryListJson.getString("queryName"));
						// strategizerQueryModel.setQueryStatement(z.toString());
						// strategizerQueryModel.setConvertedQueryStatement(z.toString());
						prepareAndSaveStrateDataSourceModel(z.toString(), strateTaskId,
								anaReportList.getReportId() != null ? anaReportList.getReportId() : "");

					}
				}
				strategizerQueryModelList.add(strategizerQueryModel);
			}
			strategizerQueryConversionRepository.saveAll(strategizerQueryModelList);
		}

		return LstOfQueryColumnAlias;

	}

	private String modifyQuerySelectItem(String queryStatement, Map<String, Alias> columnAliasMap) {
		// TODO Auto-generated method stub

		try {
			Select stmt = (Select) CCJSqlParserUtil.parse(queryStatement);

			for (SelectItem selectItem : ((PlainSelect) stmt.getSelectBody()).getSelectItems()) {
				selectItem.accept(new SelectItemVisitorAdapter() {
					@Override
					public void visit(SelectExpressionItem item) {
						// System.out.println(item.toString());
						item.setAlias(columnAliasMap.get(selectItem.toString()));

					}
				});
			}

			return stmt.toString();

		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private String fetchAliasName(String selectColumnName, List<QueryColumnModel> queryColumnList) {
		// TODO Auto-generated method stub

		Iterator<QueryColumnModel> iter = queryColumnList.iterator();

		System.out.println("Select Column NAme::" + selectColumnName);

		while (iter.hasNext()) {
			boolean flag = false;

			QueryColumnModel queryColumnModel = iter.next();

			String expression = queryColumnModel.getExpression();
			// System.out.println("Expression::"+expression);

			// if(expression.startsWith("@Aggregate_Aware(\")"))
			if (expression.startsWith("@Aggregate_Aware(")) {
				// System.out.println("Inside Aggregate Aware");
				flag = true;
				expression = StrategizerUtility.extractFunctionName(expression);
			}

			if (expression.contains(",") && flag) {
				String splitColumn[] = expression.split(",");

				for (int i = 0; i < splitColumn.length; i++) {
					String expressionName = splitColumn[i];

					expressionName = expressionName.replaceAll("\\s", "");
					selectColumnName = selectColumnName.replaceAll("\\s", "");

					// System.out.println("Expression NAme::"+expressionName);
					// System.out.println("Select Column NAme::"+selectColumnName);

					if (expressionName.equalsIgnoreCase(selectColumnName)) {
						return queryColumnModel.getColumnName();
					}
				}
			} else {
				expression = expression.replaceAll("\\s", "");
				selectColumnName = selectColumnName.replaceAll("\\s", "");

				if (expression.equalsIgnoreCase(selectColumnName)) {
					return queryColumnModel.getColumnName();
				} else if (selectColumnName.equalsIgnoreCase("MAX('user1')")
						&& expression.equalsIgnoreCase("MAX(@Variable('BOUSER'))")) {
					return queryColumnModel.getColumnName();
				}
			}

		}

		return null;
	}

	public void prepareAndSaveStrateDataSourceModel(String sql, int strateTaskId, String reportId)
			throws JSQLParserException {
		List<StrategizerDatasourceModel> list = new ArrayList<StrategizerDatasourceModel>();
		Statements statements = null;
		Select stmt = (Select) CCJSqlParserUtil.parse(sql);
		if (((PlainSelect) stmt.getSelectBody()).getJoins() != null) {
			for (Join sqlJoin : ((PlainSelect) stmt.getSelectBody()).getJoins()) {
				StrategizerDatasourceModel request = new StrategizerDatasourceModel();
				String expression = sqlJoin.getOnExpression().toString();
				if (expression.contains("(")) {
					int position = expression.toString().indexOf("(");
					expression = expression.toString().substring(position + 1, expression.toString().length() - 1);
				}
				if (sqlJoin.isCross()) {
					request.setType("cross");
				} else if (sqlJoin.isFull()) {
					request.setType("full");
				} else if (sqlJoin.isInner()) {
					request.setType("inner");
				} else if (sqlJoin.isLeft()) {
					request.setType("left");
				} else if (sqlJoin.isOuter()) {
					request.setType("outer");
				} else if (sqlJoin.isRight()) {
					request.setType("right");
				} else if (sqlJoin.isNatural()) {
					request.setType("natural");
				} else if (sqlJoin.isSemi()) {
					request.setType("semi");
				} else if (sqlJoin.isSimple()) {
					request.setType("simple");
				} else if (sqlJoin.isStraight()) {
					request.setType("straight");
				} else if (sqlJoin.isWindowJoin()) {
					request.setType("window");
				}
				String[] splitByEqualTo = expression.split("\\=");
				String[] splitByDot = splitByEqualTo[0].split("\\.");
				request.setReportId(reportId);
				request.setStratTaskId(strateTaskId);
				request.setLtable(splitByDot[0].trim());
				request.setLcolumn(splitByDot[1].trim());
				String[] splitByDot1 = splitByEqualTo[1].split("\\.");
				request.setRtable(splitByDot1[0].trim());
				request.setRcolumn(splitByDot1[1].trim());
				list.add(request);
			}
			for (int i = 0; i < list.size(); i++) {
				int initialcount = i;
				if (i > 0) {
					int previousvalue = 1;
					int count = 0;
					while (previousvalue <= initialcount) {
						if (list.get(i).getLtable().equals(list.get(previousvalue - 1).getLtable())
								|| list.get(i).getLtable().equals(list.get(previousvalue - 1).getRtable())) {
							count++;
						}
						initialcount--;
						previousvalue++;
					}
					if (count <= 0) {
						String ltable1 = list.get(i).getLtable().trim();
						String Rtable2 = list.get(i).getRtable().trim();
						String ltablecolum=list.get(i).getLcolumn().trim();
						String rtablecolum=list.get(i).getRcolumn().trim();
						list.get(i).setLtable(Rtable2);
						list.get(i).setRtable(ltable1);
						list.get(i).setLcolumn(ltablecolum);
						list.get(i).setRcolumn(rtablecolum);
						if (list.get(i).getType().equals("left")) {
							list.get(i).setType("right");
						} else if (list.get(i).getType().equals("right")) {
							list.get(i).setType("left");
						}
					}
				}
			}
		} else {
			try {
				statements = CCJSqlParserUtil.parseStatements(sql);
			} catch (JSQLParserException e) {
				e.printStackTrace();
			}
			List<net.sf.jsqlparser.statement.Statement> lis = statements.getStatements();
			List<String> tableList = new ArrayList<String>();
			for (int i = 0; i < lis.size(); i++) {
				Select selectStatement = (Select) lis.get(i);
				TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
				tableList.addAll(tablesNamesFinder.getTableList(selectStatement));
				for (String table : tableList) {
					StrategizerDatasourceModel request = new StrategizerDatasourceModel();
					request.setReportId(reportId);
					request.setStratTaskId(strateTaskId);
					request.setType("No Join");
					if (table.contains(".")) {
						String tablearray[] = table.toString().split("\\.");
						request.setLtable(tablearray[0].trim());
					} else {
						request.setLtable(table.trim());
					}
					list.add(request);
				}
			}
		}
		strategizerDatasourceModelRepository.saveAll(list);
	}
}