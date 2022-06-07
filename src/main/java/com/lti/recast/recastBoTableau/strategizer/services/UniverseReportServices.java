package com.lti.recast.recastBoTableau.strategizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.recast.recastBoTableau.strategizer.entity.UniverseReport;
import com.lti.recast.recastBoTableau.strategizer.repository.UniverseReportRepository;



@Service
public class UniverseReportServices {
	
	@Autowired
	private UniverseReportRepository universeReportRepository;
	
	
	public UniverseReport findUniverseReportDetailsByPrjRptAnalysisId(Integer prjRptAnalysisId){
		
		UniverseReport lstUniverseReport = universeReportRepository.findByPrjRptAnalysisId(prjRptAnalysisId);
		return lstUniverseReport;
	}
 
}
