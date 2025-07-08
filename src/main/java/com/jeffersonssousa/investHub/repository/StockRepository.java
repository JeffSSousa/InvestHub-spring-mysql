package com.jeffersonssousa.investHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.investHub.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String>{

}
