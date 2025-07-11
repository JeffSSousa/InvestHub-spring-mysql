package com.jeffersonssousa.investHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.investHub.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
