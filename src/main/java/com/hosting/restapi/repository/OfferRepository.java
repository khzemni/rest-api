package com.hosting.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.restapi.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
	public List<Offer> findByCreationFinished(Boolean creationFinished);
	public List<Offer> findByIsPublished(Boolean isPublished);
	public List<Offer> findByHostId(Long hostId);

}
