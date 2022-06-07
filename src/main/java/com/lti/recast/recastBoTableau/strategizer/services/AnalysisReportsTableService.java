package com.lti.recast.recastBoTableau.strategizer.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsTable;
import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportTableRepository;



@Service
public class AnalysisReportsTableService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalysisReportsTableService.class);
	
	@Autowired
	AnalysisReportTableRepository analysisReportTableRepository;
	
	@Transactional
	public List<AnalysisReportsTable> findAnalyserReportTableDataByReportIdAndTaskId(String reportId,Integer taskId){
		logger.info("Fetching Analyser Report Data by Report Id.");
		List<AnalysisReportsTable> lstAnalysisReportTable = analysisReportTableRepository.findByReportIdAndTaskId(reportId,taskId);
		logger.info("Analyser Report Data by Report Id."+lstAnalysisReportTable.toString());
	
		return lstAnalysisReportTable;
	}

}
