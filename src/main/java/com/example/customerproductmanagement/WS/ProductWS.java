package com.example.customerproductmanagement.WS;

import com.example.customerproductmanagement.Service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductWS {
    @Autowired
    private ProductService productService;

    private static final Logger log = LogManager.getLogger(ProductWS.class);

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<String> softDeleteProduct(@PathVariable Integer pid) {
        log.info("url: /products/delete/{}", pid);
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
        log.info("url: /products/update/{}", pid);
        productService.updateProduct(pid, bookTitle, bookGenre, bookPrice, bookQuantity);
        return ResponseEntity.ok("Product updated successfully.");
    }
}
