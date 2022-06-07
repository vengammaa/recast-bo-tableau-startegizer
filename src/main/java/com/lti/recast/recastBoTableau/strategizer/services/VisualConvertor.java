
package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lti.recast.recastBoTableau.strategizer.dto.StrategizerCalculatedFormulaModel;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;

public class VisualConvertor {

	public static HashMap<String, String> selfJoin(List<AnalysisReportsVisualization> visualDetailsList) {
		HashMap<String, String> selfJoinMap = new HashMap<String, String>();
		for (int i = 0; i < visualDetailsList.size(); i++) {
			if (visualDetailsList.get(i).getElementType().equals("Page Zone")) {
				selfJoinMap.put(visualDetailsList.get(i).getElementName(), visualDetailsList.get(i).getElementId());
			}
		}
		return selfJoinMap;
	}

	public static List<StrategizerCalculatedFormulaModel> tableauCalculatedFormula(
			List<AnalysisReportsVisualization> filteredVisualDetailsList, List<AnalysisReport> analysisReportList,
			List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaModelList) {
		
		
		return strategizerCalculatedFormulaModelList;
		
	}
	public static List<StrategizerCalculatedColumn> visualTableauCalculatedFormula(
			List<AnalysisReportsVisualization> filteredVisualDetailsList, List<AnalysisReport> analysisReportList,
			List<StrategizerCalculatedColumn> strategizerCalculatedFormulaModelList,int stratTaskId) {

		List<String> elementIdList = new ArrayList<String>();

		for (AnalysisReportsVisualization visualDetailsList : filteredVisualDetailsList) {
			if (visualDetailsList.getElementType().equalsIgnoreCase("VTable")) {
				elementIdList.add(visualDetailsList.getElementId());
			}
		}
          int count=0;
		for (AnalysisReportsVisualization visualDetailsList : filteredVisualDetailsList) {
			if ((visualDetailsList.getElementType().equalsIgnoreCase("VTable")||visualDetailsList.getElementType().equalsIgnoreCase("HTable"))&&count==0) {
			     count=count+1;
				JSONArray JSONArrayVariableList = null;
				for (AnalysisReport analysisReport :analysisReportList)
				{
					JSONArrayVariableList = new JSONArray(analysisReport.getVariableList()!=null?analysisReport.getVariableList():"");
					
					
				}
				for (Object variableObj : JSONArrayVariableList) {
					JSONObject jsonVarObj = (JSONObject) variableObj;
					StrategizerCalculatedColumn stratategizerCalculatedModelObj = new StrategizerCalculatedColumn();
					stratategizerCalculatedModelObj.setStratTaskId(stratTaskId);
					stratategizerCalculatedModelObj.setReportId(
							visualDetailsList.getReportId() != null ? visualDetailsList.getReportId()
									: "");
					stratategizerCalculatedModelObj
							.setReportTabId(visualDetailsList.getReportTabId() != null
									? visualDetailsList.getReportTabId()
									: "");
					stratategizerCalculatedModelObj
							.setReportName(visualDetailsList.getReportName() != null
									? visualDetailsList.getReportName()
									: "");
					stratategizerCalculatedModelObj.setColumnQualification(jsonVarObj.getString("qualification"));
					stratategizerCalculatedModelObj.setFormula(jsonVarObj.getString("definition"));
					stratategizerCalculatedModelObj.setCalculatedFormula("");
					strategizerCalculatedFormulaModelList.add(stratategizerCalculatedModelObj);
					
				}
				
			}
			if (visualDetailsList.getElementType().equalsIgnoreCase("Visualization")) {
				
				JSONArray chartAxesJSONArray = new JSONArray(visualDetailsList.getChartAxes());

				for (Object chartObj : chartAxesJSONArray) {
					JSONObject chartJSONObj = (JSONObject) chartObj;

					if (!chartJSONObj.isNull("formulaList")) {
						JSONArray formulaJSONArray = chartJSONObj.getJSONArray("formulaList");

						for (Object formulaObj : formulaJSONArray) {
							JSONObject formulaJSONObj = (JSONObject) formulaObj;
							StrategizerCalculatedColumn stratategizerCalculatedModelObj = new StrategizerCalculatedColumn();
						 if (formulaJSONObj.isNull("dataObjectId") && !(formulaJSONObj.isNull("name"))) {

								String formulaName = formulaJSONObj.getString("name");

								Pattern p = Pattern.compile("\\[(.*?)\\]");
								Matcher m = p.matcher(formulaName);

								while (m.find()) {

									stratategizerCalculatedModelObj.setStratTaskId(stratTaskId);

									stratategizerCalculatedModelObj.setReportId(
											visualDetailsList.getReportId() != null ? visualDetailsList.getReportId()
													: "");
									stratategizerCalculatedModelObj
											.setReportTabId(visualDetailsList.getReportTabId() != null
													? visualDetailsList.getReportTabId()
													: "");
									stratategizerCalculatedModelObj
											.setReportName(visualDetailsList.getReportName() != null
													? visualDetailsList.getReportName()
													: "");
									stratategizerCalculatedModelObj.setColumnQualification(formulaJSONObj.getString("qualification"));
									stratategizerCalculatedModelObj.setFormula(formulaName.substring(1));
									stratategizerCalculatedModelObj.setCalculatedFormula("");

									strategizerCalculatedFormulaModelList.add(stratategizerCalculatedModelObj);
								}
							}

						}
					}

				}
			} 

		}
		return strategizerCalculatedFormulaModelList;
	}

	public static List<StrategizerCalculatedFormulaModel> fetchVariableList(List<AnalysisReport> analysisReportList,
			List<StrategizerCalculatedFormulaModel> strategizerCalculatedFormulaModelList,
			List<StrategizerQueryConversion> strategizerQueryConversionList) {
		for (AnalysisReport x : analysisReportList) {
			Integer reportTabId = 0;

			String variables = x.getVariableList() != null ? x.getVariableList() : "";
			String tabList = x.getTabList() != null ? x.getTabList() : "";
			JSONArray tabJSONArray = new JSONArray(tabList);
			for (int i = 0; i < tabJSONArray.length(); i++) {
				JSONObject tabJSONObj = tabJSONArray.getJSONObject(i);
				reportTabId = tabJSONObj.getInt("reportTabId");
			}
			if (!variables.equalsIgnoreCase("[]")) {
				JSONArray variableJSONArray = new JSONArray(variables);

				if (variableJSONArray.length() > 0) {
					for (int i = 0; i < variableJSONArray.length(); i++) {
						StrategizerCalculatedFormulaModel strategizerCalculatedFormula = new StrategizerCalculatedFormulaModel();
						JSONObject variableJSONObj = variableJSONArray.getJSONObject(i);

						strategizerCalculatedFormula.setReportId(x.getReportId());
						strategizerCalculatedFormula.setReportTabId(reportTabId.toString());
						strategizerCalculatedFormula
								.setColumnQualification(variableJSONObj.getString("qualification") != null
										? variableJSONObj.getString("qualification")
										: "");
						strategizerCalculatedFormula.setFormula(
								variableJSONObj.getString("name") + " " + variableJSONObj.getString("definition"));
						strategizerCalculatedFormula.setCalculatedFormula("");
						strategizerCalculatedFormulaModelList.add(strategizerCalculatedFormula);
					}

				}

			}
		}
		return strategizerCalculatedFormulaModelList;
	}

}
