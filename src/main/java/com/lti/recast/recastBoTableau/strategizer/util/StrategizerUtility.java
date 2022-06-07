package com.lti.recast.recastBoTableau.strategizer.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsTable;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;

public class StrategizerUtility {

	// Logic for Parsing function and removing Aggregate Aware

	public static String extractFunctionName(String functions) {
		// TODO Auto-generated method stub

		// String result="";

		if (functions.startsWith("@Aggregate_Aware(")) {
			functions = functions.substring(17, functions.length() - 1);
			System.out.println(functions);
		}

		return functions;

	}

	public static List<String[]> convertToListStringArray(List<List<String>> csvDetailsList) {
		List<String[]> l = new ArrayList<String[]>();
		for (int i = 0; i < csvDetailsList.size(); i++) {
			List<String> ar = csvDetailsList.get(i);
			String str[] = new String[ar.size()];

			Object[] objArr = ar.toArray();
			int j = 0;
			for (Object obj : objArr) {
				str[j++] = (String) obj;
			}
			l.add(str);
		}
		return l;
	}

	public static String counterPadding(int counter) {
		// TODO Auto-generated method stub

		String returnCounter = "";

		if (counter <= 9) {
			returnCounter = "00" + counter;
		} else if (counter > 9 && counter < 100) {
			returnCounter = "0" + counter;
		} else if (counter > 100) {
			returnCounter = "" + counter;
		}

		return returnCounter;
	}

	public static String calYPosition(Double conyPosition, Double maxHeight) {
		Double yPositionPixel = conyPosition * (double) 96;
		Integer yPositionPixelInt = (int) Math.round(yPositionPixel);
		String res = yPositionPixelInt.toString();
		Double maxHeightPixel = maxHeight * (double) 96;
		// Double height = (double)650;
		Double height = (double) 720;
		if (maxHeightPixel > height) {
			Double resyPosition = (yPositionPixel * height) / maxHeightPixel;
			Integer resyPositionInt = (int) Math.round(resyPosition);
			res = resyPositionInt.toString();
		}
		return res;
	}

	public static String calXPosition(Double conxPosition, Double maxWidth) {
		// TODO Auto-generated method stub

		Double xPositionPixel = conxPosition * (double) 96;
		Integer xPositionPixelInt = (int) Math.round(conxPosition * (double) 96);
		String res = xPositionPixelInt.toString();
		Double maxWidthPixel = maxWidth * (double) 96;
		Double width = (double) 1280;
		if (maxWidthPixel > width) {
			Double resxPosition = ((conxPosition * (double) 96) * width) / maxWidthPixel;
			Integer resxPositionInt = (int) Math.round(resxPosition);
			res = resxPositionInt.toString();
		}
		return res;
	}

	public static String fetchCalculatedFormula(String formulaValue, AnalysisReportsVisualization visualDetails,
			List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaList) {
		// TODO Auto-generated method stub

		System.out.println("fomrula in Cell::" + formulaValue);

		Iterator<StrategizerCalculatedFormulaModel> iter = strategizerCalculatedFormulaList.iterator();
		while (iter.hasNext()) {
			StrategizerCalculatedFormulaModel s = iter.next();

			if (s.getReportId().equalsIgnoreCase(visualDetails.getReportId())
					&& s.getReportTabId().equalsIgnoreCase(visualDetails.getReportTabId())
					&& formulaValue.equalsIgnoreCase(s.getFormula())) {
				return s.getCalculatedFormula().split("=")[0];
			}

		}

		return formulaValue.substring(2, formulaValue.length() - 1);

	}

	public static String checkTableAliasFunctionName(String functionName, List<AnalysisReportsTable> reportTableList) {
		// TODO Auto-generated method stub

		System.out.println("Function name before::" + functionName);

		Iterator<AnalysisReportsTable> iter = reportTableList.iterator();

		while (iter.hasNext()) {
			AnalysisReportsTable tableObj = iter.next();

			String tableAlias = tableObj.getTableAliasNames();

			if (tableAlias != null) {
				String tableName = tableObj.getTableNames();

				if (functionName.contains(tableName)) {
					functionName = functionName.replace(tableName, tableAlias);
					return functionName;
				}

			}

		}

		return functionName;
	}

	public static List<String> parseQuery(String queryStatement) {

		List<String> selectList = new LinkedList<String>();
		try {
			Select select = (Select) CCJSqlParserUtil.parse(queryStatement);

			List<SelectItem> selectItemList = ((PlainSelect) select.getSelectBody()).getSelectItems();

			selectItemList.forEach(x -> {

				selectList.add(x.toString());

			});

		} catch (JSQLParserException e) {
			e.printStackTrace();
		}

		return selectList;
	}
}
