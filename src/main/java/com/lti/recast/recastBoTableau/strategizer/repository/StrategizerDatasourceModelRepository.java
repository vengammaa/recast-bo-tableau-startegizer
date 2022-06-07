package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerDatasourceModel;


public interface StrategizerDatasourceModelRepository extends JpaRepository<StrategizerDatasourceModel,Integer>{
	
	List<StrategizerDatasourceModel> findByStratTaskId(int stratTaskId);

}
