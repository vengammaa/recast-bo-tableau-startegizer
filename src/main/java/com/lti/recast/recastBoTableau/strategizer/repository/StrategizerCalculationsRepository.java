package com.lti.recast.recastBoTableau.strategizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.recast.recastBoTableau.strategizer.entity.StrategizerCalculations;

public interface StrategizerCalculationsRepository extends JpaRepository<StrategizerCalculations,Integer>{

	List<StrategizerCalculations> findByStratTaskId(int startTaskId);
}
