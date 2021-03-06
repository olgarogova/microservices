package com.mycompany.app.service.order.domain.repository;

import com.mycompany.app.service.order.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByOrderNumber(String orderNumber);
}
