package com.example.customerproductmanagement.Service;

import ExceptionUtil.CustomExceptionHandler;
import ExceptionUtil.ProductNotFoundException;
import com.example.customerproductmanagement.DAO.ProductDao;
import com.example.customerproductmanagement.DTO.ProductDetail;
import com.example.customerproductmanagement.DTO.ProductRequest;
import com.example.customerproductmanagement.DTO.ProductResponse;
import com.example.customerproductmanagement.Entity.ProductsEntity;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    public void softDeleteProduct(Integer pid) {
        productDao.softDeleteProduct(pid);
    }

    @Transactional
    public void updateProduct(Integer pid, String bookTitle, String bookGenre, BigDecimal bookPrice, int bookQuantity) {
        productDao.updateProduct(pid, bookTitle, bookGenre, bookPrice, bookQuantity);
    }
}
