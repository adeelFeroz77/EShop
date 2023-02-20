package com.EShop.EShop.controller;

import com.EShop.EShop.dto.CustomerDto;
import com.EShop.EShop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    private ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/customer/{id}")
    private ResponseEntity<CustomerDto> getAllCustomers(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping("/customer")
    private ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.saveCustomer(customerDto));
    }

    @PatchMapping("/customer/{id}")
    private ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(customerService.updateByFields(id,fields));
    }

    @DeleteMapping("/customer/{id}")
    private ResponseEntity<String> deleteCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.deleteById(id));
    }
}
