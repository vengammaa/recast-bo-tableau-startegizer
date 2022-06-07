package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.AnalysisSemanticColumns;

public interface AnalysisSemanticColumnRepository extends JpaRepository<AnalysisSemanticColumns, Integer> {

	List<AnalysisSemanticColumns> findByTaskId (int taskId);
}
