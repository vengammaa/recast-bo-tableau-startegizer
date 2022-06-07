package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.UniverseReport;

public interface UniverseReportRepository extends JpaRepository<UniverseReport, Integer>{
	
	
	

	UniverseReport findByPrjRptAnalysisId(Integer prjRptAnalysisId);


}
