package com.example.customerproductmanagement.WS;

import com.example.customerproductmanagement.DTO.CustomerRequest;
import com.example.customerproductmanagement.DTO.CustomerResponse;
import com.example.customerproductmanagement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerWS {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/retrieveCustomers")
    public ResponseEntity<CustomerResponse> retrieveCustomers(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.retrieveCustomers(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/countAllCustomers")
    public ResponseEntity<Long> countAllCustomers() {
        long count = customerService.countAllCustomers();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
