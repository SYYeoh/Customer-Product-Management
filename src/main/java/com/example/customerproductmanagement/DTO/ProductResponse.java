package com.example.customerproductmanagement.DTO;

import CommonUtil.Paging;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse extends Paging {
    private ProductDetail productDetail;
    private List<ProductDetail> productDetailList;
}
