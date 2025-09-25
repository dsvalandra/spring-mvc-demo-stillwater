package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AgentRepository agentRepository;

    public Optional<Quote> createQuote(Long customerId, Long agentId, Quote quoteDetails) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<Agent> agentOpt = agentRepository.findById(agentId);

        if (customerOpt.isPresent() && agentOpt.isPresent()) {
            quoteDetails.setCustomer(customerOpt.get());
            quoteDetails.setAgent(agentOpt.get());
            return Optional.of(quoteRepository.save(quoteDetails));
        }
        return Optional.empty();
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Optional<Quote> getQuoteById(Long id) {
        return quoteRepository.findById(id);
    }
}