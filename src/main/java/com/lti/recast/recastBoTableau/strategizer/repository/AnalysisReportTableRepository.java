package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisReportsTable;

public interface AnalysisReportTableRepository extends JpaRepository<AnalysisReportsTable, Integer>{
	
	//List<AnalysisReportsTable> deleteByTaskId(int taskId);
	
	//List<AnalysisReportsTable> findByTaskId (int taskId);

	//List<AnalysisReportsTable> findByReportId(String reportId);

	List<AnalysisReportsTable> findByReportIdAndTaskId(String reportId,Integer taskId);

	List<AnalysisReportsTable> findByTaskId(int analysisTaskId);
}
