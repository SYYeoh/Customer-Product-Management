package com.example.customerproductmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_item", schema = "customer_product", catalog = "")
public class OrderItemEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_ITEM")
    @SequenceGenerator(name = "SEQ_ORDER_ITEM", sequenceName = "SEQ_ORDER_ITEM", allocationSize = 1)
    @Id
    @Column(name = "sku", nullable = false)
    private int sku;
    @Basic
    @Column(name = "order_id", nullable = false)
    private int orderId;
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
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @OneToMany(mappedBy = "orderItemEntity")
    private Collection<OrderHistoryEntity> orderHistoriesBySku;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    private OrdersEntity ordersEntity;
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false, insertable = false, updatable = false)
    private ProductsEntity productsEntity;
}
