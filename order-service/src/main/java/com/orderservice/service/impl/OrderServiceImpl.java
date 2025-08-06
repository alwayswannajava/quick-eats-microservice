package com.orderservice.service.impl;

import com.orderservice.controller.mapper.OrderMapper;
import com.orderservice.domain.Order;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import com.orderservice.service.exception.OrderNotFoundException;
import com.orderservice.service.exception.OrderProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void create(Order order) {
        log.info("Creating order: {}", order);
        try {
            log.info("Trying to save order: {}", order);
            orderRepository.save(order);
            log.info("Order created successfully: {}", order);
        } catch (Exception e) {
            log.error("Error while creating order: {}", order, e);
            throw new OrderProcessingException("Error while creating order: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Order update(String orderId, Order order) {
        log.info("Updating order with id: {}", orderId);
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderProcessingException("Order not found with id: " + orderId));
        log.info("Existing order found: {}", existingOrder);
        try {
            orderMapper.toOrder(existingOrder, order);
            return orderRepository.save(existingOrder);
        } catch (Exception e) {
            log.error("Error while updating order with id: {}", orderId, e);
            throw new OrderProcessingException("Error while updating order: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Order fetch(String orderId) {
        log.info("Fetching order with id: {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    @Transactional
    @Modifying
    public void delete(String orderId) {
        log.info("Deleting order with id: {}", orderId);
        orderRepository.deleteById(orderId);
        log.info("Order with id: {} deleted successfully", orderId);
    }

    @Override
    public Order fetchByPhone(String phone) {
        return orderRepository.findByContactPhone(phone);
    }
}
