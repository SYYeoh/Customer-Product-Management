package com.example.customerproductmanagement.DTO;

import CommonUtil.Paging;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse extends Paging {
    private CustomerDetail customerDetail;
    private List<CustomerDetail> customerDetailList;
}
