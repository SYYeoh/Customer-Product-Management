package com.example.customerproductmanagement.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetail {
    private int orderId;
    private int custId;
    private String first_name;
    private String last_name;
    private String emailOffice;
    private String emailPersonal;
    private String addressLine;
    private String city;
    private String postal;
    private String sku;

    private List<OrderItemDetail> orderItemDetailList;
}
