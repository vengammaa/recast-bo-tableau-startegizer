package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.RptTargetConParamType;

public interface RptTargetConParamTypeRepository extends JpaRepository<RptTargetConParamType, String> {
	List<RptTargetConParamType> findByReportTypeCode(String code);
}
