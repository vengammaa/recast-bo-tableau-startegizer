package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.recast.recastBoTableau.strategizer.entity.ReportStrategizer;

public interface ReportStrategizerRepository extends JpaRepository<ReportStrategizer, Integer>{

	List<ReportStrategizer> findAllByProjectReportTargetConId(int projectReportTargetConId);

	
	@Query(value="SELECT * FROM recast.report_strategizer where analysis_task_id=?1", nativeQuery = true)
	public ReportStrategizer findByAnalysisTaskId(Integer analysisTaskId);

	
}
