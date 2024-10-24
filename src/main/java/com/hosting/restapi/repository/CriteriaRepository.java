package com.hosting.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.restapi.entity.Criteria;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
	
	

}
