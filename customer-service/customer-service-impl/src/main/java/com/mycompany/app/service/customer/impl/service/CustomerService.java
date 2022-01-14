package com.mycompany.app.service.customer.impl.service;

import com.mycompany.app.service.customer.domain.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getAllCustomers(String customerName);
    CustomerEntity getCustomerById(int customerId);
    CustomerEntity saveCustomer(CustomerEntity customerEntity);
    CustomerEntity updateCustomer(int customerId, CustomerEntity updatedCustomerEntity);
    void deleteCustomer(int customerId);
}
