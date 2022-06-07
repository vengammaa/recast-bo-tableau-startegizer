package com.lti.recast.recastBoTableau.strategizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.recast.recastBoTableau.strategizer.entity.SapboTableauMapping;

@Repository
public interface SapboTableauMappingRepository extends JpaRepository<SapboTableauMapping,Integer> {
	
	public SapboTableauMapping findBySapboComponent(String sapboComponent);
}
