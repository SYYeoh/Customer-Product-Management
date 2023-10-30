package com.example.customerproductmanagement.Service;

import com.example.customerproductmanagement.DAO.CustomerDao;
import com.example.customerproductmanagement.DTO.*;
import com.example.customerproductmanagement.Entity.CustomersEntity;
import com.example.customerproductmanagement.Entity.OrderHistoryEntity;
import com.example.customerproductmanagement.Entity.OrdersEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public void saveCustomer(CustomersEntity entity) {
        customerDao.persistCustomer(entity);
    }

    public CustomerResponse retrieveCustomers(CustomerRequest request) {
        List<CustomersEntity> customers = customerDao.retrieveCustomers(request);

        List<CustomerDetail> customerDetails = customers.stream()
                .map(customer -> {
                    CustomerDetail customerDetail = new CustomerDetail();
                    customerDetail.setCustId(customer.getCustId());
                    customerDetail.setFirstName(customer.getFirstName());
                    customerDetail.setLastName(customer.getLastName());
                    customerDetail.setEmailOffice(customer.getEmailOffice());
                    customerDetail.setEmailPersonal(customer.getEmailPersonal());
                    customerDetail.setAddressLine(customer.getAddressLine());
                    customerDetail.setCity(customer.getCity());
                    customerDetail.setPostal(customer.getPostal());
                    customerDetail.setStatus(customer.getStatus());

                    return customerDetail;
                })
                .collect(Collectors.toList());

        CustomerResponse response = new CustomerResponse();
        response.setCustomerDetailList(customerDetails);
        response.setMaxPerPage(customerDetails.size());
        response.setTotalRecord(customerDao.countTotalCustomers(request));

        return response;
    }

    public CustomerResponse countAllCustomers() {
        return customerDao.countTotalCustomers();
    }

    public CustomerResponse getOrdersByCustomerId(CustomerRequest request) {
        CustomerResponse response = new CustomerResponse();

        if (request != null && request.getCustomerDetail() != null) {
            Integer custId = request.getCustomerDetail().getCustId();
            if (custId != null) {
                List<CustomersEntity> customers = customerDao.getOrdersByCustomerId(request);

                List<CustomerDetail> customerDetails = customers.stream()
                        .map(customer -> {
                            CustomerDetail customerDetail = new CustomerDetail();
                            customerDetail.setCustId(customer.getCustId());
                            customerDetail.setFirstName(customer.getFirstName());
                            customerDetail.setLastName(customer.getLastName());
                            customerDetail.setEmailOffice(customer.getEmailOffice());
                            customerDetail.setEmailPersonal(customer.getEmailPersonal());
                            customerDetail.setAddressLine(customer.getAddressLine());
                            customerDetail.setCity(customer.getCity());
                            customerDetail.setPostal(customer.getPostal());
                            customerDetail.setStatus(customer.getStatus());

                            // Retrieve and map orders for the customer
                            List<OrderDetail> orderDetails = customer.getOrdersEntityCollection().stream()
                                    .map(order -> {
                                        OrderDetail orderDetail = new OrderDetail();
                                        orderDetail.setOrderId(order.getOrderId());

                                        List<OrderItemDetail> orderItemDetails = order.getOrderItemEntityCollection().stream()
                                                .map(orderItem -> {
                                                    OrderItemDetail orderItemDetail = new OrderItemDetail();

                                                    return orderItemDetail;
                                                })
                                                .collect(Collectors.toList());

                                        orderDetail.setOrderItemDetailList(orderItemDetails);
                                        return orderDetail;
                                    })
                                    .collect(Collectors.toList());

                            customerDetail.setOrderDetailList(orderDetails);
                            return customerDetail;
                        })
                        .collect(Collectors.toList());

                response.setCustomerDetailList(customerDetails);
                response.setMaxPerPage(customerDetails.size());
                response.setTotalRecord(customerDao.countTotalCustomers(request));
            }
        }

        return response;
    }

}
