package com.lti.recast.recastBoTableau.strategizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.recast.recastBoTableau.strategizer.entity.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, String>{

}
