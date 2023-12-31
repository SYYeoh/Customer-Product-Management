package com.example.customerproductmanagement.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDetail {
    private Integer custId;
    private String firstName;
    private String lastName;
    private String emailOffice;
    private String emailPersonal;
    private String addressLine;
    private String city;
    private String postal;
    private String status;

    private List<OrderDetail> orderDetailList;
}
