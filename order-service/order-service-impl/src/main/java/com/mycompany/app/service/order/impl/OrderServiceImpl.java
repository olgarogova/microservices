package com.mycompany.app.service.order.impl;

import com.mycompany.app.service.order.domain.entity.OrderEntity;
import com.mycompany.app.service.order.domain.entity.OrderStatus;
import com.mycompany.app.service.order.impl.exceptions.ResourceNotFoundException;
import com.mycompany.app.service.order.domain.repository.OrderRepository;
import com.mycompany.app.service.order.impl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final StubProductService stubProductService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, StubProductService stubProductService) {
        this.orderRepository = orderRepository;
        this.stubProductService = stubProductService;
    }

    @Override
    public List<com.mycompany.app.service.order.domain.entity.OrderEntity> getAllOrders(String orderNumber){
        List<OrderEntity> orderEntities = new ArrayList<>();
        if (orderNumber == null){
            orderEntities.addAll(orderRepository.findAll());
        } else {
            orderEntities.addAll(orderRepository.findAllByOrderNumber(orderNumber));
        }
        if (orderEntities.isEmpty()) {
            throw new ResourceNotFoundException("Order not found");
        }
        return orderEntities;
    }

    @Override
    public OrderEntity getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepository
                    .save(new OrderEntity(orderEntity.getOrderNumber(), orderEntity.getOrderDate(), orderEntity.getTotalAmount(), orderEntity.getOrderStatus()));
    }

    @Override
    public OrderEntity updateOrder(int orderId, OrderEntity updatedOrderEntity) {
        OrderEntity orderData = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderData.setOrderNumber(updatedOrderEntity.getOrderNumber());
        orderData.setOrderDate(updatedOrderEntity.getOrderDate());
        orderData.setTotalAmount(updatedOrderEntity.getTotalAmount());
        orderData.setOrderStatus(updatedOrderEntity.getOrderStatus());
        orderRepository.save(orderData);
        return orderData;
    }

    @Override
    public OrderEntity paidOrder (int orderId, OrderEntity orderEntity){
        stubProductService.reserveProducts(orderEntity);
        orderEntity.setOrderStatus(OrderStatus.PAYED);
        return orderEntity;
    }

    @Override
    public void deleteOrder(int orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Order with id " + orderId + " does not exist");
        }
    }
}