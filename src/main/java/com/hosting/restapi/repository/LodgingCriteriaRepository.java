package com.hosting.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.restapi.entity.LodgingCriteria;

public interface LodgingCriteriaRepository extends JpaRepository<LodgingCriteria, Long> {

	public List<LodgingCriteria> findByLodgingId(Long lodgingId);
}
