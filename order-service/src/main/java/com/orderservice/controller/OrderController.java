package com.orderservice.controller;

import com.orderservice.controller.mapper.OrderMapper;
import com.orderservice.dto.request.CreateOrderRequest;
import com.orderservice.dto.request.UpdateOrderRequest;
import com.orderservice.dto.response.FetchOrderResponse;
import com.orderservice.dto.response.UpdateOrderResponse;
import com.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createOrder(CreateOrderRequest createOrderRequest) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Received request to create order");
        log.info("Order created successfully");
        log.info("------------------------POST REQUEST END------------------------");
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<FetchOrderResponse> fetchOrder(@PathVariable String id) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Received request to fetch order with id: {}", id);
        log.info("Fetched order successfully");
        log.info("------------------------GET REQUEST END------------------------");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateOrderResponse> updateOrder(@PathVariable String id, UpdateOrderRequest updateOrderRequest) {
        log.info("------------------------PUT REQUEST------------------------");
        log.info("Received request to update order with id: {}", id);
        log.info("Order updated successfully");
        log.info("------------------------PUT REQUEST END------------------------");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        log.info("------------------------DELETE REQUEST------------------------");
        log.info("Received request to delete order with id: {}", id);
        orderService.delete(id);
        log.info("Order deleted successfully");
        log.info("------------------------DELETE REQUEST END------------------------");
        return ResponseEntity.ok().build();
    }
}
