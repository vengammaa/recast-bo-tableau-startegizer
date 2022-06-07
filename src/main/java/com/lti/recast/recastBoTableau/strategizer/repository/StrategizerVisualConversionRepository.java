package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerVisualizationConversion;

public interface StrategizerVisualConversionRepository extends JpaRepository<StrategizerVisualizationConversion,Integer>{

	List<StrategizerVisualizationConversion> findByStratTaskId(int startTaskId);
	
	//@Query("SELECT distinct stratTaskId,reportId,reportTabId FROM StrategizerVisualizationConversion where stratTaskId=1")
	//List<StrategizerVisualizationConversion> findDistinctByStratTaskId(int startTaskId);

}
