package com.example.customerproductmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products_history", schema = "customer_product", catalog = "")
public class ProductsHistoryEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT_HISTORY")
    @SequenceGenerator(name = "SEQ_PRODUCT_HISTORY", sequenceName = "SEQ_PRODUCT_HISTORY", allocationSize = 1)
    @Id
    @Column(name = "product_history_id", nullable = false)
    private int productHistoryId;
    @Basic
    @Column(name = "pid", nullable = false)
    private int pid;
    @Basic
    @Column(name = "book_title", nullable = false, length = 255)
    private String bookTitle;
    @Basic
    @Column(name = "book_genre", nullable = false, length = 100)
    private String bookGenre;
    @Basic
    @Column(name = "book_price", nullable = false, precision = 2)
    private BigDecimal bookPrice;
    @Basic
    @Column(name = "book_quantity", nullable = false)
    private int bookQuantity;
    @Basic
    @Column(name = "prod_status", nullable = true, length = 100)
    private String prodStatus;
    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsEntity;

}
