package com.lti.recast.recastBoTableau.strategizer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.recast.recastBoTableau.strategizer.repository.AnalysisReportsVisualizationRepository;

@Service
public class AnalysisReportVisualizationService {

	
	private static final Logger logger = LoggerFactory.getLogger(AnalysisReportVisualizationService.class);
	
	@Autowired
	AnalysisReportsVisualizationRepository analysisReportVisualizationRepository;
	

	
}
