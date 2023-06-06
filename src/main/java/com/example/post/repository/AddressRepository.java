package com.example.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.example.post.entities.Address;

@Repository
@RestResource
public interface AddressRepository extends JpaRepository<Address, Long> {

}
