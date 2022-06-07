package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportTableRepository;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportsVisualizationRepository;

@Service
public class AnalysisReportService {
	
private static final Logger logger = LoggerFactory.getLogger(AnalysisReportService.class);
	
	@Autowired
	AnalysisReportRepository analysisReportRepository;
	
	@Autowired
	AnalysisReportTableRepository analysisReportTableRepository;
	
	@Autowired
	AnalysisReportsVisualizationRepository analysisReportsVisualizationRepository;
	
	
	@Transactional
	public List<AnalysisReport> findByReportIdAndPrjRptAnalysisId(String reportId, Integer prjRptAnalysisId){
		logger.info("Fetching Analyser Report Data by Report Id.");
		List<AnalysisReport> lstAnalysisReport = analysisReportRepository.findByReportIdAndPrjRptAnalysisId(reportId,prjRptAnalysisId);
		logger.info("Analyser Report Data by Report Id."+lstAnalysisReport.toString());
	
		return lstAnalysisReport;
	}

	

	
	public JSONObject fetchListOfQueryStatement(List<AnalysisReport> lstAnalysisReport){
		JSONArray queryStatements= new JSONArray();
		JSONObject prepareQueryRequest= new JSONObject();
		if(!lstAnalysisReport.isEmpty())
		{
			for (AnalysisReport analysisReport : lstAnalysisReport) {
				String queryList = analysisReport.getQueryList();
				JSONArray queryJSONArray = new JSONArray(queryList);
				for(Object y:queryJSONArray)
				{
					JSONObject lstQueryStatements = (JSONObject) y;
					queryStatements= lstQueryStatements.getJSONArray("queryStatements");
				}
			}
			
			
		}
		return prepareQueryRequest;
		
	}
	
	


}
