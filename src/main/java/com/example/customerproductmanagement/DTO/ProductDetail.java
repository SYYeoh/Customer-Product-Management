package com.example.customerproductmanagement.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetail {
    private Integer pid;
    private String bookTitle;
    private String bookGenre;
    private BigDecimal bookPrice;
    private Integer bookQuantity;
    private String status;
    private Boolean deleted;
}
