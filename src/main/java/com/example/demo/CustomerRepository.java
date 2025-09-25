package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    
	// The corrected query with multiple JOIN FETCH clauses
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.policies p LEFT JOIN FETCH c.quotes q LEFT JOIN FETCH c.agent a WHERE c.id = ?1")
    Optional<Customer> findByIdWithDetails(Long id);
}