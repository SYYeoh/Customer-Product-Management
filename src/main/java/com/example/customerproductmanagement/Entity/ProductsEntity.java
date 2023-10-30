package com.example.customerproductmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE pid = ?")
@Where(clause = "deleted = false")
@Data
@Table(name = "products", schema = "customer_product", catalog = "")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;
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
    @Column(name = "status", nullable = true, length = 255)
    private String status;
    @Basic
    @Column(name = "deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "productsEntity")
    private Collection<OrderItemEntity> orderItemEntityCollection;
    @OneToMany(mappedBy = "productsEntity")
    private Collection<ProductsHistoryEntity> productsHistoryEntityCollection;
}
