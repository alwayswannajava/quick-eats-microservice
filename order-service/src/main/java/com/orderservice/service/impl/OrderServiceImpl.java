package com.orderservice.service.impl;

import com.orderservice.domain.Order;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import com.orderservice.service.exception.OrderNotFoundException;
import com.orderservice.service.exception.OrderProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void create(Order order) {
        log.info("Creating order: {}", order);
        try {
            orderRepository.save(order);
            log.info("Order created successfully: {}", order);
        } catch (Exception e) {
            log.error("Error while creating order: {}", order, e);
            throw new OrderProcessingException("Error while creating order: " + e.getMessage());
        }
    }

    @Override
    public Order update(String orderId, Order order) {
        log.info("Updating order with id: {}", orderId);
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderProcessingException("Order not found with id: " + orderId));

        try {
            return orderRepository.save(existingOrder);
        } catch (Exception e) {
            log.error("Error while updating order with id: {}", orderId, e);
            throw new OrderProcessingException("Error while updating order: " + e.getMessage());
        }
    }

    @Override
    public Order fetch(String orderId) {
        log.info("Fetching order with id: {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public void delete(String orderId) {
        log.info("Deleting order with id: {}", orderId);
        orderRepository.deleteById(orderId);
    }
}
