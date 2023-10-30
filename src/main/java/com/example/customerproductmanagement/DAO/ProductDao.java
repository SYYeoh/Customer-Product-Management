package com.example.customerproductmanagement.DAO;

import ExceptionUtil.ProductNotFoundException;
import com.example.customerproductmanagement.Entity.ProductsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class ProductDao{
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LogManager.getLogger(ProductDao.class);
    @Transactional
    public void softDeleteProduct(Integer pid) {
        ProductsEntity product = entityManager.find(ProductsEntity.class, pid);
        if (product != null) {
            product.setDeleted(true);
        }
    }

    @Transactional
    public void updateProduct(Integer pid, String bookTitle, String bookGenre, BigDecimal bookPrice, int bookQuantity) {
        log.info("Updating product with ID: " + pid);
        ProductsEntity product = entityManager.find(ProductsEntity.class, pid);
        if (product == null) {
            log.warn("Product with ID " + pid + " not found");
            throw new ProductNotFoundException("Product with ID " + pid + " not found");
        }

        product.setBookTitle(bookTitle);
        product.setBookGenre(bookGenre);
        product.setBookPrice(bookPrice);
        product.setBookQuantity(bookQuantity);
        log.info("Product updated successfully: " + product);
    }
}
