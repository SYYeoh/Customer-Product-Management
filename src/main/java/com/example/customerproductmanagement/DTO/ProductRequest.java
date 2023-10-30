package com.example.customerproductmanagement.DTO;

import CommonUtil.Paging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest  extends Paging {
    private ProductDetail productDetail;
    private List<ProductDetail> productDetailList;
}
