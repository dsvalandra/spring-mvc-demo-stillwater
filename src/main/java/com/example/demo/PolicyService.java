package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Policy> createPolicyForCustomer(Long customerId, Policy policyDetails) {
        return customerRepository.findById(customerId).map(customer -> {
            policyDetails.setCustomer(customer);
            return Optional.of(policyRepository.save(policyDetails));
        }).orElse(Optional.empty());
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }
}