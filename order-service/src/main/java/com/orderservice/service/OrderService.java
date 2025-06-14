package com.orderservice.service;

import com.orderservice.domain.Order;

public interface OrderService {
    void create(Order order);

    Order update(String orderId, Order order);

    Order fetch(String orderId);

    void delete(String orderId);
}
