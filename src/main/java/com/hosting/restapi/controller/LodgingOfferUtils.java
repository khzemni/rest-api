package com.hosting.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.entity.Criteria;
import com.hosting.restapi.entity.LodgingType;
import com.hosting.restapi.repository.CriteriaRepository;
import com.hosting.restapi.repository.LodgingTypeRepository;

@RestController
@RequestMapping("/api/v1/lodging")
@CrossOrigin(origins = "*")
public class LodgingOfferUtils {
	
	@Autowired
	private LodgingTypeRepository lodgingTypeRepository;
	@Autowired 
	private CriteriaRepository criteriaRepository;
	
	@GetMapping(value="/types")
	public List<LodgingType> listLodgingTypes() {
		return this.lodgingTypeRepository.findAll();
	}
	
	@GetMapping(value="/criterias")
	public List<Criteria> listLodgingCriterias() {
		return this.criteriaRepository.findAll();
	}

}
