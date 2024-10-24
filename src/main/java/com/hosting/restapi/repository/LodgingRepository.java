package com.hosting.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.restapi.entity.Lodging;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long>{

}
