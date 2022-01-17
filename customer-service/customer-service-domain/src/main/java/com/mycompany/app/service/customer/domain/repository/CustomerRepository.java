package com.mycompany.app.service.customer.domain.repository;

import com.mycompany.app.service.customer.domain.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findAllByCustomerName(String customerName);
}
