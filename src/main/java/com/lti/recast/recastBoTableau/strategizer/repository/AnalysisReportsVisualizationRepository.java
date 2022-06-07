package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsVisualization;



public interface AnalysisReportsVisualizationRepository extends JpaRepository<AnalysisReportsVisualization,Integer> {
	
	//public List<AnalysisReportsVisualization> findByTaskId(Integer taskId);

	//public List<AnalysisReportsVisualizationRepository> findByReportId(String reportId);

	public List<AnalysisReportsVisualization> findByTaskIdAndReportId(int analysisTaskId,String reportId);

	public List<AnalysisReportsVisualization> findByTaskId(int analysisTaskId);

}
