package com.jeffersonssousa.investHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.investHub.entities.BillingAddress;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long>{

}
