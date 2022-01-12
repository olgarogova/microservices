package com.mycompany.app.service.order.impl.service;

import com.mycompany.app.service.order.domain.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<com.mycompany.app.service.order.domain.entity.OrderEntity> getAllOrders(String orderNumber);
    OrderEntity getOrderById(int orderId);
    OrderEntity createOrder(OrderEntity orderEntity);
    OrderEntity updateOrder(int orderId, OrderEntity updatedOrderEntity);
    void deleteOrder(int orderId);
}
