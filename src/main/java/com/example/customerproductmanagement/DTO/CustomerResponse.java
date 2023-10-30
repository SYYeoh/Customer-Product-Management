package com.example.customerproductmanagement.DTO;

import CommonUtil.Paging;
import com.example.customerproductmanagement.Entity.OrdersEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse extends Paging {
    private CustomerDetail customerDetail;
    private List<CustomerDetail> customerDetailList;

    private List<OrderDetail> orderDetailList;
}
