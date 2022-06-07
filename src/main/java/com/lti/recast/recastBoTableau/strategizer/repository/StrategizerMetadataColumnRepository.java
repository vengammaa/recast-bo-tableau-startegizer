package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerMetadataColumns;


public interface StrategizerMetadataColumnRepository extends JpaRepository<StrategizerMetadataColumns,Integer>{

	List<StrategizerMetadataColumns> findByStratTaskId(int stratTaskId);
}
