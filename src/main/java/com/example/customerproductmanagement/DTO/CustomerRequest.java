package com.example.customerproductmanagement.DTO;

import CommonUtil.Paging;
import com.example.customerproductmanagement.Entity.OrdersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest extends Paging {
    private CustomerDetail customerDetail;
    private List<CustomerDetail> customerDetailList;
}
