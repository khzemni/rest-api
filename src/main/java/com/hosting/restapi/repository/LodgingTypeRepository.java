package com.hosting.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.restapi.entity.LodgingType;

@Repository
public interface LodgingTypeRepository extends JpaRepository<LodgingType, Long> {

}
