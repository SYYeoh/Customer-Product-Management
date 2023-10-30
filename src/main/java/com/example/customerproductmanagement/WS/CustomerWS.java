package com.example.customerproductmanagement.WS;

import com.example.customerproductmanagement.DTO.CustomerRequest;
import com.example.customerproductmanagement.DTO.CustomerResponse;
import com.example.customerproductmanagement.Service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerWS {
    @Autowired
    private CustomerService customerService;
    private static final Logger log = LogManager.getLogger(CustomerWS.class);
    private static final String log_info_response = "Response: {}";

    @PostMapping("/retrieveCustomers")
    public ResponseEntity<CustomerResponse> retrieveCustomers(@RequestBody CustomerRequest request) {
        log.info("url: /customers/retrieveCustomers");
        CustomerResponse response = customerService.retrieveCustomers(request);
        log.info(log_info_response, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/countAllCustomers")
    public ResponseEntity<CustomerResponse> countAllCustomers() {
        log.info("url: /customers/countAllCustomers");
        CustomerResponse response = customerService.countAllCustomers();
        log.info(log_info_response, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getOrdersByCustomerId")
    public ResponseEntity<CustomerResponse> getOrdersByCustomerId(@RequestBody CustomerRequest request) {
        log.info("url: /customers/getOrdersByCustomerId");
        CustomerResponse response = customerService.getOrdersByCustomerId(request);
        log.info(log_info_response, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
