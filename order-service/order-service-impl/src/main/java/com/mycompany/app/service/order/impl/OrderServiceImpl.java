package com.mycompany.app.service.order.impl;

import com.mycompany.app.service.order.domain.dto.CustomerDto;
import com.mycompany.app.service.order.domain.entity.OrderEntity;
import com.mycompany.app.service.order.domain.entity.OrderStatus;
import com.mycompany.app.service.order.domain.dto.ProductDto;
import com.mycompany.app.service.order.impl.exceptions.ResourceNotFoundException;
import com.mycompany.app.service.order.domain.repository.OrderRepository;
import com.mycompany.app.service.order.impl.openfeign.CustomerServiceClient;
import com.mycompany.app.service.order.impl.openfeign.ProductServiceClient;
import com.mycompany.app.service.order.impl.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final CustomerServiceClient customerServiceClient;

    static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceClient productServiceClient, CustomerServiceClient customerServiceClient) {
        this.orderRepository = orderRepository;
        this.productServiceClient = productServiceClient;
        this.customerServiceClient = customerServiceClient;
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
    public OrderEntity createOrder(OrderEntity orderEntity, int customerId) {
        customerServiceClient.getCustomerById(customerId);
        return orderRepository
                .save(new OrderEntity(orderEntity.getOrderNumber(), orderEntity.getOrderDate(), orderEntity.getCustomerId(), orderEntity.getTotalAmount(), orderEntity.getOrderStatus()));

    }

    @Override
    public OrderEntity updateOrder(int orderId, OrderEntity updatedOrderEntity) {
        OrderEntity orderData = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderData.setOrderNumber(updatedOrderEntity.getOrderNumber());
        orderData.setOrderDate(updatedOrderEntity.getOrderDate());
        orderData.setCustomerId(updatedOrderEntity.getCustomerId());
        orderData.setTotalAmount(updatedOrderEntity.getTotalAmount());
        orderData.setOrderStatus(updatedOrderEntity.getOrderStatus());
        orderRepository.save(orderData);
        return orderData;
    }

    @Override
    public OrderEntity paidOrder (int orderId, List<ProductDto> productDtos){
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        for(ProductDto productDto: productDtos) {
            productServiceClient.deleteProduct(productDto.getProductId());
        }
        orderEntity.setOrderStatus(OrderStatus.PAYED);
        orderRepository.save(orderEntity);
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