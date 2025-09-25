package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // You need this import for the List return type

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	List<Policy> findByCustomer(Customer customer);
}