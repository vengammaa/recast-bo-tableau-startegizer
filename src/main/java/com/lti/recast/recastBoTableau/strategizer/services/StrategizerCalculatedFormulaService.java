package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculatedColumn;
import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerQueryConversion;

@Service
public class StrategizerCalculatedFormulaService {

	public List<StrategizerCalculatedColumn> saveFormulaData(
			List<AnalysisReportsVisualization> filteredVisualDetailsList, List<AnalysisReport> analysisReportList,
			List<StrategizerQueryConversion> strategizerQueryConversionList,int stratTaskId) {
		
		List<StrategizerCalculatedColumn> strategizerCalculatedFormulaModelList = new ArrayList<>();
		
		strategizerCalculatedFormulaModelList = VisualConvertor.visualTableauCalculatedFormula(
				filteredVisualDetailsList, analysisReportList, strategizerCalculatedFormulaModelList,stratTaskId);

		/*
		 * strategizerCalculatedFormulaModelList =
		 * VisualConvertor.fetchVariableList(analysisReportList,
		 * strategizerCalculatedFormulaModelList, strategizerQueryConversionList);
		 */

		return strategizerCalculatedFormulaModelList;
	}

}
