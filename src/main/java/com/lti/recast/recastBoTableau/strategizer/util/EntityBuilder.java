package com.lti.recast.recastBoTableau.strategizer.util;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.lti.recast.recastBoTableau.strategizer.dto.PrjRptTargetConParamsModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ProjectReportTargetConModel;
import com.lti.recast.recastBoTableau.strategizer.dto.QueryColumnAliasModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ReportStrategizerModel;
import com.lti.recast.recastBoTableau.strategizer.dto.ReportTypeModel;
import com.lti.recast.recastBoTableau.strategizer.dto.RptTargetConParamTypeModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StatusModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerQueryModel;
import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerVisualizationConvertor;
import com.lti.recast.recastBoTableau.strategizer.dto.TaskStatusModel;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;
import com.lti.recast.recastBoTableau.strategizer.entity.PrjRptTargetConParams;
import com.lti.recast.recastBoTableau.strategizer.entity.ProjectReportTargetCon;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportStrategizer;
import com.lti.recast.recastBoTableau.strategizer.entity.ReportType;
import com.lti.recast.recastBoTableau.strategizer.entity.RptTargetConParamType;
import com.lti.recast.recastBoTableau.strategizer.entity.Status;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculations;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerVisualizationConversion;
import com.lti.recast.recastBoTableau.strategizer.entity.TaskStatus;
import com.lti.recast.recastBoTableau.strategizer.repository.SapboTableauMappingRepository;

public class EntityBuilder {

	@Autowired
	private SapboTableauMappingRepository sapBoTableauMappingRepository;
	static Double constval = 3600.0;

	public static ReportStrategizer reportStrategizerBuilder(ReportStrategizerModel reportTaskModel) {

		ReportStrategizer p = new ReportStrategizer();

		p.setAnalysisTaskId(reportTaskModel.getAnalysisTaskId());
		p.setTaskName(reportTaskModel.getTaskName());
		p.setTaskStatus(taskStatusEntityBuilder(reportTaskModel.getTaskStatus()));
		p.setSourceReportType(reportTaskModel.getSourceReportType());
		p.setTargetReportType(reportTaskModel.getTargetReportType());
		p.setProjectReportTargetCon(
				projectReportTargetConEntityBuilderCopy(reportTaskModel.getProjectReportTargetCon()));
		return p;

	}

	public static TaskStatus taskStatusEntityBuilder(TaskStatusModel tm) {
		TaskStatus t = new TaskStatus();
		t.setCode(tm.getCode());
		t.setName(tm.getName());
		return t;

	}

	public static ProjectReportTargetCon projectReportTargetConEntityBuilderCopy(ProjectReportTargetConModel pm) {
		System.out.println("Project ReportTarget ConModel:: " + pm);
		ProjectReportTargetCon p = new ProjectReportTargetCon();
		p.setId(pm.getId());
//		p.setReportType(reportTypeEntityBuilder(pm.getReportType()));
		p.setName(pm.getName());
//		p.setPrjRptTargetConParams(pm.getPrjRptTargetConParams().stream().map(EntityBuilder::prjRptTargetConParamsEntityBuilder).collect(Collectors.toSet()));
		System.out.println("\nProject Report Method::" + p);
		return p;
	}

	public static StrategizerQueryConversion StrategizerQueryEntityBuilder(StrategizerQueryModel x) {
		// TODO Auto-generated method stub

		StrategizerQueryConversion strategizerQueryConversionObj = new StrategizerQueryConversion();

		strategizerQueryConversionObj.setConvertedQueryName(x.getConvertedQueryName());
		strategizerQueryConversionObj.setConvertedQueryStatement(x.getConvertedQueryStatement());
		strategizerQueryConversionObj.setDatabaseName(x.getDatabaseName());
		strategizerQueryConversionObj.setDatabaseType(x.getDatabaseType());
		strategizerQueryConversionObj.setHostname(x.getHostname());
		strategizerQueryConversionObj.setQueryStatement(x.getQueryStatement());
		strategizerQueryConversionObj.setReportId(x.getReportId());
		strategizerQueryConversionObj.setQueryName(x.getQueryName());
		strategizerQueryConversionObj.setReportName(x.getReportName());

		return strategizerQueryConversionObj;
	}

	public static StrategizerCalculatedColumn StrategizerCalculatedColumnBuilder(JSONObject x, int stratTaskId) {
		// TODO Auto-generated method stub

		StrategizerCalculatedColumn strategizerCalculatedColumnObj = new StrategizerCalculatedColumn();
		strategizerCalculatedColumnObj.setStratTaskId(stratTaskId);
		strategizerCalculatedColumnObj.setReportId(x.getString("reportId") != null ? x.getString("reportId") : "");
		strategizerCalculatedColumnObj
				.setReportTabId(x.getString("reportTabId") != null ? x.getString("reportTabId") : "");
		strategizerCalculatedColumnObj.setFormula(x.getString("formula") != null ? x.getString("formula") : "");
		strategizerCalculatedColumnObj.setCalculatedFormula(
				x.getString("calculatedFormula").substring(1) != null ? x.getString("calculatedFormula").substring(1)
						: "");
		strategizerCalculatedColumnObj.setColumnQualification(
				x.getString("columnQualification") != null ? x.getString("columnQualification") : "");
		strategizerCalculatedColumnObj
				.setReportName(x.getString("reportName") != null ? x.getString("reportName") : "");
		return strategizerCalculatedColumnObj;

	}

	public static StrategizerVisualizationConversion strategizerVisualConvertor(StrategizerVisualizationConvertor x) {
		// TODO Auto-generated method stub

		StrategizerVisualizationConversion y = new StrategizerVisualizationConversion();

		y.setColor(x.getColor());
		y.setFont(x.getFont());
		y.setFormulaName(x.getFormulaName());
		y.setParentElement(x.getParentElement());
		y.setReportId(x.getReportId());
		y.setReportTabId(x.getReportTabId());
		y.setReportTabName(x.getReportTabName());
		y.setSourceComponentName(x.getSourceComponentName());
		y.setSourceMinimalHeight(x.getSourceMinimalHeight());
		y.setSourceMinimalWidth(x.getSourceMinimalWidth());
		y.setSourcePositionX(x.getSourcePositionX());
		y.setSourcePositionY(x.getSourcePositionY());
		y.setTargetComponentName(x.getTargetComponentName());
		y.setTargetMinimalHeight(x.getTargetMinimalHeight());
		y.setTargetMinimalWidth(x.getTargetMinimalWidth());
		y.setTargetPositionX(x.getTargetPositionX());
		y.setTargetPositionY(x.getTargetPositionY());
		y.setValueType(x.getValueType());

		return y;
	}

	public static StrategizerVisualizationConversion prepareStrategizerVisualConversion(
			AnalysisReportsVisualization visualDetailsList, int stratTaskId,
			List<QueryColumnAliasModel> queryColumnAliasLst, double headerHeight, HashMap<String, Integer> elementMap,
			 List<StrategizerCalculations> strCalculation,int counter,StrategizerVisualizationConversion strategizerVisualConverter) {
		
	
		//StrategizerVisualizationConversion strategizerVisualConverter = new StrategizerVisualizationConversion();
		strategizerVisualConverter.setStratTaskId(stratTaskId);
		Double maxWidth = Double
				.parseDouble(visualDetailsList.getMaximumWidth() != null ? visualDetailsList.getMaximumWidth() : "");
		Double maxHeight = Double
				.parseDouble(visualDetailsList.getMaximumHeight() != null ? visualDetailsList.getMaximumHeight() : "");
		
		strategizerVisualConverter
				.setReportId(visualDetailsList.getReportId() != null ? visualDetailsList.getReportId() : "");
		strategizerVisualConverter.setReportName(
				visualDetailsList.getReportName() != null ? visualDetailsList.getReportName() + counter : "");
		strategizerVisualConverter
				.setReportTabId(visualDetailsList.getReportTabId() != null ? visualDetailsList.getReportTabId() : "");
		String reportTabeName = "";
		for (QueryColumnAliasModel columnLst : queryColumnAliasLst) {

			reportTabeName = reportTabeName + "," + columnLst.getAliasName();
		}
		for(StrategizerCalculations straCal:strCalculation)
		{
			reportTabeName= reportTabeName + "," + straCal.getCalculationName();
		}
		strategizerVisualConverter.setReportTabName(!reportTabeName.isEmpty() ? reportTabeName.substring(1) : "");
		strategizerVisualConverter
				.setSourcePositionX(visualDetailsList.getxPosition() != null ? visualDetailsList.getxPosition() : "");
		strategizerVisualConverter
				.setSourcePositionY(visualDetailsList.getyPosition() != null ? visualDetailsList.getyPosition() : "");
		strategizerVisualConverter.setSourceMinimalWidth(
				visualDetailsList.getMinimalWidth() != null ? visualDetailsList.getMinimalWidth() : "");
		strategizerVisualConverter.setSourceMinimalHeight(
				visualDetailsList.getMinimalHeight() != null ? visualDetailsList.getMinimalHeight() : "");

		Double resXposition = Double
				.parseDouble(visualDetailsList.getxPosition() != null && !visualDetailsList.getxPosition().isEmpty() ? visualDetailsList.getxPosition() : "");
		Double resYposition = Double
				.parseDouble(visualDetailsList.getyPosition() != null && !visualDetailsList.getxPosition().isEmpty()  ? visualDetailsList.getyPosition() : "");
		if (visualDetailsList.getHorizontalAnchorType() != null
				&& !visualDetailsList.getHorizontalAnchorType().equals("None")) {
			String type = visualDetailsList.getHorizontalAnchorType();
			if (type.equals("End")) {

				// int pos = elementMap.get(visualDetailsList.getHorizontalAnchorId());

				resXposition += Double.parseDouble(visualDetailsList.getMinimalWidth());
				String hid = visualDetailsList.getHorizontalAnchorId();

				while (!hid.trim().isEmpty()) {
					if (elementMap.containsKey(hid)) {
						int position = elementMap.get(hid);
						resXposition += Double.parseDouble(visualDetailsList.getxPosition());
						hid = visualDetailsList.getHorizontalAnchorId();
					}
				}

			} else if (type.equals("Begin")) {

				String hid = visualDetailsList.getHorizontalAnchorId();

				while (!hid.trim().isEmpty()) {
					if (elementMap.containsKey(hid)) {
						// int position = elementMap.get(hid);
						resXposition += Double.parseDouble(visualDetailsList.getxPosition());
						hid = visualDetailsList.getHorizontalAnchorId();
					}
				}

			}

		}
		if (visualDetailsList.getVerticalAnchorType() != null
				&& !visualDetailsList.getVerticalAnchorType().equals("None")) {
			String type = visualDetailsList.getVerticalAnchorType();
			if (type.equals("End")) {

				// int pos = elementMap.get(visualDetailsList.getVerticalAnchorId());

				resYposition += Double.parseDouble(visualDetailsList.getMinimalHeight());
				String vid = visualDetailsList.getVerticalAnchorId();

				while (!vid.trim().isEmpty()) {
					if (elementMap.containsKey(vid)) {
						int position = elementMap.get(vid);
						resYposition += Double.parseDouble(visualDetailsList.getyPosition());
						vid = visualDetailsList.getVerticalAnchorId();
					}
				}
			} else if (type.equals("Begin")) {

				String vid = visualDetailsList.getVerticalAnchorId();

				while (!vid.trim().isEmpty()) {
					if (elementMap.containsKey(vid)) {
						// int position = elementMap.get(vid);
						resYposition += Double.parseDouble(visualDetailsList.getyPosition());
						vid = visualDetailsList.getVerticalAnchorId();
					}
				}
			}
		}
		strategizerVisualConverter.setFont(visualDetailsList.getFont() != null ? visualDetailsList.getFont() : "");
		strategizerVisualConverter
				.setColor(visualDetailsList.getBackgroundColor() != null ? visualDetailsList.getBackgroundColor() : "");
		strategizerVisualConverter
				.setParentElement(visualDetailsList.getElementName() != null ? visualDetailsList.getElementName() : "");	
		return strategizerVisualConverter;
	}

	public String prepareFormulaForHTableAndVTable(String chartAxesString, List<AnalysisReport> analysisReportList,
			String elementType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonHTable = new JSONObject();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");

		}

		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");

			JSONArray namejsonArray = new JSONArray();
			JSONArray textjsonArray = new JSONArray();
			for (int k = 0; k < formulaJSONArray.length(); k++) {
				JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);

				String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
						? formulaJSONObj.getString("dataObjectId")
						: "";
				int counter = 0;
				for (Object jsonVariable : jsonVariableList) {

					JSONObject jsonVar = (JSONObject) jsonVariable;
					if (dataObjectId.equals(jsonVar.getString("id"))) {
						counter = counter + 1;
						if (jsonVar.getString("dataType").equals("Numeric")
								&& jsonVar.getString("qualification").equals("Measure")) {
							String columnName = jsonVar.getString("name").substring(2,
									jsonVar.getString("name").length() - 1);
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());
								}
							}
						} else {
							String columnName = jsonVar.getString("name");
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}
						}
					}

				}
				if (counter == 0) {
					if (formulaJSONObj.getString("dataType").equals("Numeric")
							&& formulaJSONObj.getString("qualification").equals("Measure")) {
						String columnName = formulaJSONObj.getString("name").substring(2,
								formulaJSONObj.getString("name").length() - 1);
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								textjsonArray.put(columnAlias.getAliasName());
							}
						}

					} else {
						String columnName = formulaJSONObj.getString("name").substring(2,
								formulaJSONObj.getString("name").length() - 1);
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								namejsonArray.put(columnAlias.getAliasName());
							}
						}

					}
				}
			}

			jsonHTable.put("type", "Table");
			JSONArray MeasureArray = new JSONArray();
			MeasureArray.put("Measure Names");
			if (elementType.equals("HTable")) {
				jsonHTable.put("cols", namejsonArray);
				jsonHTable.put("rows", MeasureArray);
			} else {
				jsonHTable.put("rows", namejsonArray);
				jsonHTable.put("cols", MeasureArray);
			}

			JSONArray filter = new JSONArray();
			JSONObject filterObj = new JSONObject();
			filterObj.put("column", "Measure Names");
			filterObj.put("from", "");
			filterObj.put("to", "");
			filterObj.put("members", textjsonArray);
			filter.put(filterObj);
			jsonHTable.put("filter", filter);

			JSONArray structure = new JSONArray();
			JSONObject structureObj = new JSONObject();
			JSONArray multipleValue = new JSONArray();
			multipleValue.put("Multiple Values");
			structureObj.put("type", "Table");
			structureObj.put("text", multipleValue);
			structure.put(structureObj);

			jsonHTable.put("structure", structure);

		}

		return jsonHTable.toString();

	}

	public String prepareFormulaForPie(String chartAxesString, List<AnalysisReport> analysisReportList,
			String chartType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		JSONArray jsonVariableList = null;
		JSONObject jsonPie = new JSONObject();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");

		}
		JSONArray namejsonArray = new JSONArray();
		JSONArray textjsonArray = new JSONArray();

		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");
			for (int k = 0; k < formulaJSONArray.length(); k++) {
				JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);

				String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
						? formulaJSONObj.getString("dataObjectId")
						: "";
				int counter = 0;
				for (Object jsonVariable : jsonVariableList) {

					JSONObject jsonVar = (JSONObject) jsonVariable;
					if (dataObjectId.equals(jsonVar.getString("id"))) {
						counter = counter + 1;
						if (jsonVar.getString("dataType").equals("Numeric")
								&& jsonVar.getString("qualification").equals("Measure")) {
							String columnName = jsonVar.getString("name").substring(2,
									jsonVar.getString("name").length() - 1);
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());
								}
							}
						} else {
							String columnName = jsonVar.getString("name");
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}
						}
					}

				}
				if (counter == 0) {
					String columnName;

					if (formulaJSONObj.getString("dataType").equals("Numeric")
							&& formulaJSONObj.getString("qualification").equals("Measure")) {

						int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
						int poition2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
						if (poition2 == position1) {
							columnName = formulaJSONObj.getString("name").substring(2,
									formulaJSONObj.getString("name").length() - 1);
						} else {
							columnName = formulaJSONObj.getString("name").substring(poition2+1,
									formulaJSONObj.getString("name").length() - 1);
						}

						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								// System.err.println("columnAilas " + columnAlias.getAliasName());
								textjsonArray.put(columnAlias.getAliasName());
								// System.err.println("textjsonArray "+ textjsonArray.toString());
							}
						}

					} else {

						int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
						int poition2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
						if (position1 == poition2) {
							columnName = formulaJSONObj.getString("name").substring(position1 + 1,
									formulaJSONObj.getString("name").length() - 1);
						} else {
							columnName = formulaJSONObj.getString("name").substring(poition2 + 1,
									formulaJSONObj.getString("name").length() - 1);
						}
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								namejsonArray.put(columnAlias.getAliasName());
								// System.err.println("namejsonArray " +namejsonArray.toString() );

							}
						}

					}
				}

			}
			JSONArray structure = new JSONArray();
			JSONObject structureObj = new JSONObject();
			if(chartType.equalsIgnoreCase("Pie"))
			{
			jsonPie.put("type", "Pie");
			structureObj.put("type", "Pie");
		
			}
			else if(chartType.equalsIgnoreCase("Doughnut"))
			{
				jsonPie.put("type", "Donut");
				structureObj.put("type", "Donut");
			}
		

			
			structureObj.put("color", namejsonArray);
			structureObj.put("wsize", textjsonArray);
			structureObj.put("size", textjsonArray);
			structure.put(structureObj);

			jsonPie.put("structure", structure);

		}

		return jsonPie.toString();

	}
	
	public String prepareFormulaForVerticalBar(String chartAxesString, List<AnalysisReport> analysisReportList,
			String ChartType, List<QueryColumnAliasModel> queryColumnAliasLst) {
		
		JSONArray jsonVariableList = null;
		JSONObject jsonVerticalBar = new JSONObject();
		JSONArray namejsonArray = new JSONArray();
		JSONArray textjsonArray = new JSONArray();
		JSONArray chartAxesJSONArray = new JSONArray(chartAxesString);
		for (AnalysisReport analysisReport : analysisReportList) {
			jsonVariableList = new JSONArray(
					analysisReport.getVariableList() != null ? analysisReport.getVariableList() : "");

		}
	

		for (int j = 0; j < chartAxesJSONArray.length(); j++) {
			
			JSONObject chartJSONObj = chartAxesJSONArray.getJSONObject(j);
			
				if (!chartJSONObj.isNull("formulaList"))
			
				{
			
			JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList") ;
		
				for (int k = 0; k < formulaJSONArray.length(); k++) {
				
				JSONObject formulaJSONObj = formulaJSONArray.getJSONObject(k);

				String dataObjectId = formulaJSONObj.getString("dataObjectId") != null
						? formulaJSONObj.getString("dataObjectId")
						: "";
				int counter = 0;
				for (Object jsonVariable : jsonVariableList) {

					JSONObject jsonVar = (JSONObject) jsonVariable;
					if (dataObjectId.equals(jsonVar.getString("id"))) {
						counter = counter + 1;
						if (jsonVar.getString("dataType").equals("Numeric")
								&& jsonVar.getString("qualification").equals("Measure")) {
							String columnName = jsonVar.getString("name").substring(2,
									jsonVar.getString("name").length() - 1);
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									namejsonArray.put(columnAlias.getAliasName());
								}
							}
						} else {
							String columnName = jsonVar.getString("name");
							for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {

								if (columnName.equals(columnAlias.getColumnName())) {
									textjsonArray.put(columnAlias.getAliasName());
								}
							}
						}
					}

				}
				if (counter == 0) {
					String columnName ;
					
					if (formulaJSONObj.getString("dataType").equals("Numeric")&& formulaJSONObj.getString("qualification").equals("Measure")) {
						
						int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
						int poition2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
						if(poition2 ==position1)
						{
							columnName = formulaJSONObj.getString("name").substring(position1+1,formulaJSONObj.getString("name").length() - 1);
						}
						else {
						columnName = formulaJSONObj.getString("name").substring(poition2+1,formulaJSONObj.getString("name").length() - 1);
						}
						
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
							//	System.err.println("columnAilas " + columnAlias.getAliasName());
								textjsonArray.put(columnAlias.getAliasName());
							//System.err.println("textjsonArray "+ textjsonArray.toString());
							}
						}

					} else {
						
						int position1 = formulaJSONObj.getString("name").toString().indexOf("[");
						int poition2 = formulaJSONObj.getString("name").toString().lastIndexOf("[");
						if(position1 == poition2)
						{
							columnName = formulaJSONObj.getString("name").substring(2,formulaJSONObj.getString("name").length() - 1);
						}
						else {
						   columnName = formulaJSONObj.getString("name").substring(12,formulaJSONObj.getString("name").length() - 1);
						}
						for (QueryColumnAliasModel columnAlias : queryColumnAliasLst) {
							if (columnName.equals(columnAlias.getColumnName())) {
								namejsonArray.put(columnAlias.getAliasName());
								//System.err.println("namejsonArray  " +namejsonArray.toString() );
								
							}
						}

					}
				}
				
			}
			
			jsonVerticalBar.put("type","Bar");
			jsonVerticalBar.put("header","Bar");
			
			if (ChartType.equals("VerticalBar")) {
				//System.err.println(namejsonArray);

				jsonVerticalBar.put("rows", textjsonArray);
				jsonVerticalBar.put("cols", namejsonArray);
				
			} else {
				jsonVerticalBar.put("rows", namejsonArray);
				jsonVerticalBar.put("cols", textjsonArray);
			}
			
			
			JSONArray structure = new JSONArray();
			JSONObject structureObj = new JSONObject();

			structureObj.put("type", "Column");
			
			structure.put(structureObj);
			
			jsonVerticalBar.put("structure", structure);

		}
		}

		return jsonVerticalBar.toString();
		
	}
	public static ProjectReportTargetCon projectReportTargetConEntityBuilder(ProjectReportTargetConModel pm) {
		System.out.println("Project ReportTarget ConModel:: " + pm);
		ProjectReportTargetCon p = new ProjectReportTargetCon();
		p.setReportType(reportTypeEntityBuilder(pm.getReportType()));
		p.setName(pm.getName());
		p.setPrjRptTargetConParams(pm.getPrjRptTargetConParams().stream().map(EntityBuilder::prjRptTargetConParamsEntityBuilder).collect(Collectors.toSet()));
		System.out.println("\nProject Report Method::" + p);
		return p;
	}
	
	public static PrjRptTargetConParams prjRptTargetConParamsEntityBuilder(PrjRptTargetConParamsModel pm) {
		System.out.println("Prj Rpt Target Con Params::" + pm);
		PrjRptTargetConParams p = new PrjRptTargetConParams();
		p.setRptTargetConParamType(rptTargetConParamTypeEntityBuilder(pm.getRptTargetConParamType()));
		p.setRptTargetConParamValue(pm.getRptTargetConParamValue());
		return p;
	}
	public static RptTargetConParamType rptTargetConParamTypeEntityBuilder(RptTargetConParamTypeModel rm) {
		System.out.println("RptTargetConParamModel::" + rm);
		RptTargetConParamType r = new RptTargetConParamType();
		r.setCode(rm.getCode());
		r.setName(rm.getName());
		r.setReportType(reportTypeEntityBuilder(rm.getReportType()));
		return r;
	}
	public static ReportType reportTypeEntityBuilder(ReportTypeModel rm) {
		ReportType r = new ReportType();
		r.setName(rm.getName());
		r.setCode(rm.getCode());
		r.setStatus(statusEntityBuilder(rm.getStatus()));
		return r;
	}
	
	public static Status statusEntityBuilder(StatusModel statusModel) {
		Status status = new Status();
		status.setName(statusModel.getName());
		status.setCode(statusModel.getCode());
		return status;
	}
}
