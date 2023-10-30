package com.example.customerproductmanagement.DTO;

import lombok.Data;

@Data
public class OrderItemDetail {
    private int sku;
    private int orderId;
    private int pid;
    private String bookTitle;
    private String bookGenre;
    private int quantity;
}
