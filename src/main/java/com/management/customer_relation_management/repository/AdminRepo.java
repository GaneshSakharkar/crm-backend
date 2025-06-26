package com.management.customer_relation_management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.customer_relation_management.entities.Admin;

public interface AdminRepo extends JpaRepository<Admin,Long> {

    Admin findByEmail(String email); 
    
    Optional<Admin> findByEmailAndName(String email,String name);
}
