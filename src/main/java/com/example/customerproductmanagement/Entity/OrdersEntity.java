package com.example.customerproductmanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders", schema = "customer_product", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER")
    @SequenceGenerator(name = "SEQ_ORDER", sequenceName = "SEQ_ORDER", allocationSize = 1)
    @Id
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
    @Column(name = "created_time", nullable = true)
    private Timestamp createdTime;
    @Basic
    @Column(name = "updated_time", nullable = true)
    private Timestamp updatedTime;
    @Basic
    @Column(name = "status", nullable = true, length = 100)
    private String status;
    @OneToMany(mappedBy = "ordersEntity")
    private Collection<OrderHistoryEntity> orderHistoryEntityCollection;
    @OneToMany(mappedBy = "ordersEntity")
    private Collection<OrderItemEntity> orderItemEntityCollection;
    @ManyToOne
    @JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
    private CustomersEntity customersEntity;
}
