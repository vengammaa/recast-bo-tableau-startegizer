package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.recast.recastBoTableau.strategizer.entity.ProjectReportTargetCon;

public interface ProjectReportTargetConRepository extends JpaRepository<ProjectReportTargetCon, Integer> {
	
	
	@Query(value = "SELECT * FROM project_report_target_con WHERE report_type_code = ?1", nativeQuery = true)
	List<ProjectReportTargetCon> findByReportTypeCode(String reportTypeCode);
}
