package com.example.customerproductmanagement.WS;

import com.example.customerproductmanagement.DTO.CustomerDetail;
import com.example.customerproductmanagement.DTO.CustomerRequest;
import com.example.customerproductmanagement.DTO.CustomerResponse;
import com.example.customerproductmanagement.Entity.CustomersEntity;
import com.example.customerproductmanagement.Entity.OrderHistoryEntity;
import com.example.customerproductmanagement.Entity.OrdersEntity;
import com.example.customerproductmanagement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity<CustomerResponse> countAllCustomers() {
        CustomerResponse response = customerService.countAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getOrdersByCustomerId")
    public ResponseEntity<CustomerResponse> getOrdersByCustomerId(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.getOrdersByCustomerId(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
