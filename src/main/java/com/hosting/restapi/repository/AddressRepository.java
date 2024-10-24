package com.hosting.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.restapi.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
