package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReport;

public interface AnalysisReportRepository extends JpaRepository<AnalysisReport, Integer>{
	
	
	//List<AnalysisReport> findByReportId (String prjRptAnalysisId);

	public List<AnalysisReport> findByReportIdAndPrjRptAnalysisId(String reportId, Integer prjRptAnalysisId);

	public List<AnalysisReport> findByPrjRptAnalysisId(int analysisTaskId);

	//List<AnalysisReport> findByPrjRptAnalysisId(int analysisTaskId);

}
