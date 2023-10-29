package com.example.customerproductmanagement.Service;

import com.example.customerproductmanagement.DAO.CustomerDao;
import com.example.customerproductmanagement.DTO.CustomerDetail;
import com.example.customerproductmanagement.DTO.CustomerRequest;
import com.example.customerproductmanagement.DTO.CustomerResponse;
import com.example.customerproductmanagement.Entity.CustomersEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public long countAllCustomers() {
        return customerDao.countTotalCustomers();
    }
}
