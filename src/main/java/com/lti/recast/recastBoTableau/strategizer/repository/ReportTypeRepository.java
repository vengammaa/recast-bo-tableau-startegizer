package com.lti.recast.recastBoTableau.strategizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.recast.recastBoTableau.strategizer.entity.ReportType;

@Repository
public interface ReportTypeRepository extends JpaRepository<ReportType, String> {
	
}
