package com.example.customerproductmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_history", schema = "customer_product", catalog = "")
public class OrderHistoryEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_HISTORY")
    @SequenceGenerator(name = "SEQ_ORDER_HISTORY", sequenceName = "SEQ_ORDER_HISTORY", allocationSize = 1)
    @Id
    @Column(name = "order_hist_id", nullable = false)
    private int orderHistId;
    @Basic
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @Basic
    @Column(name = "cust_id", nullable = false)
    private int custId;
    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;
    @Basic
    @Column(name = "email_office", nullable = true, length = 255)
    private String emailOffice;
    @Basic
    @Column(name = "email_personal", nullable = true, length = 255)
    private String emailPersonal;
    @Basic
    @Column(name = "address_line", nullable = true, length = 255)
    private String addressLine;
    @Basic
    @Column(name = "city", nullable = true, length = 255)
    private String city;
    @Basic
    @Column(name = "postal", nullable = true, length = 255)
    private String postal;
    @Basic
    @Column(name = "sku", nullable = false)
    private int sku;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "order_status", nullable = true, length = 100)
    private String orderStatus;
    @Basic
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;
    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    private OrdersEntity ordersEntity;
    @ManyToOne
    @JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
    private CustomersEntity customersEntity;
    @ManyToOne
    @JoinColumn(name = "sku", referencedColumnName = "sku", nullable = false, insertable = false, updatable = false)
    private OrderItemEntity orderItemEntity;
}
