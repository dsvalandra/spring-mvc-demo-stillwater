package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.HashSet; 

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
	private PolicyRepository policyRepository;
	@Autowired
	private QuoteRepository quoteRepository;


    public Optional<Customer> getCustomerWithDetails(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            List<Policy> policiesList = policyRepository.findByCustomer(customer);
            List<Quote> quotesList = quoteRepository.findByCustomer(customer);

            // Convert List to Set before setting
            customer.setPolicies(new HashSet<>(policiesList));
            customer.setQuotes(new HashSet<>(quotesList));

            return Optional.of(customer);
        }
        return Optional.empty();
    }
    
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    customer.setAddress(customerDetails.getAddress());
                    customer.setPhoneNumber(customerDetails.getPhoneNumber());
                    customer.setDateOfBirth(customerDetails.getDateOfBirth());
                    return Optional.of(customerRepository.save(customer));
                }).orElse(Optional.empty());
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}