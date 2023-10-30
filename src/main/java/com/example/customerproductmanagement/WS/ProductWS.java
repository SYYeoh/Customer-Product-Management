package com.example.customerproductmanagement.WS;

import com.example.customerproductmanagement.DTO.*;
import com.example.customerproductmanagement.Entity.ProductsEntity;
import com.example.customerproductmanagement.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductWS {
    @Autowired
    private ProductService productService;

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<String> softDeleteProduct(@PathVariable Integer pid) {
        productService.softDeleteProduct(pid);
        return ResponseEntity.ok("Product soft-deleted successfully.");
    }

    @PutMapping("/update/{pid}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Integer pid,
            @RequestParam String bookTitle,
            @RequestParam String bookGenre,
            @RequestParam BigDecimal bookPrice,
            @RequestParam int bookQuantity
    ) {
        productService.updateProduct(pid, bookTitle, bookGenre, bookPrice, bookQuantity);
        return ResponseEntity.ok("Product updated successfully.");
    }
}
