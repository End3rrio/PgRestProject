package com.example.pgrestproject.controller;

import com.example.pgrestproject.exception.ResourceNotfoundException;
import com.example.pgrestproject.model.Customer;
import com.example.pgrestproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pgrestproject.model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer  = customerRepository.findById(id).orElseThrow(() -> new ResourceNotfoundException("Customer does not exist with id : " + id));
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer (@PathVariable Long id, @RequestBody Customer customerDetail){
        Customer customer  = customerRepository.findById(id).orElseThrow(() -> new ResourceNotfoundException("Customer does not exist with id : " + id));
        customer.builder()
                .name(customerDetail.getName())
                .email(customerDetail.getEmail())
                .phone(customerDetail.getPhone())
                .build();
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
        Customer customer  = customerRepository.findById(id).orElseThrow(() -> new ResourceNotfoundException("Customer does not exist with id : " + id));
        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
