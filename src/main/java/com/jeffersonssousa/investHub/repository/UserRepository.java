package com.jeffersonssousa.investHub.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonssousa.investHub.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
