package com.example.customerproductmanagement.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customers", schema = "customer_product", catalog = "")
public class CustomersEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUSTOMER")
    @SequenceGenerator(name = "SEQ_CUSTOMER", sequenceName = "SEQ_CUSTOMER", allocationSize = 1)
    @Id
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
    @Column(name = "status", nullable = true, length = 100)
    private String status;
    @OneToMany(mappedBy = "customersEntity")
    private Collection<OrderHistoryEntity> orderHistoryEntityCollection;
    @OneToMany(mappedBy = "customersEntity")
    private Collection<OrdersEntity> ordersEntityCollection;
}
