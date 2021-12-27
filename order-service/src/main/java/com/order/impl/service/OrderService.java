package com.order.impl.service;

import com.order.domain.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> getAllOrders(String orderNumber);
    OrderEntity getOrderById(int orderId);
    OrderEntity createOrder(OrderEntity orderEntity);
    OrderEntity updateOrder(int orderId, OrderEntity updatedOrderEntity);
    void deleteOrder(int orderId);
}
