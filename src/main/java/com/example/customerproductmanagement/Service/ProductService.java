package com.example.customerproductmanagement.Service;

import com.example.customerproductmanagement.DAO.ProductDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
