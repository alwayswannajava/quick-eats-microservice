package com.orderservice.controller;

import com.orderservice.controller.mapper.OrderMapper;
import com.orderservice.dto.request.CreateOrderRequest;
import com.orderservice.dto.request.UpdateOrderRequest;
import com.orderservice.dto.response.FetchOrderResponse;
import com.orderservice.dto.response.UpdateOrderResponse;
import com.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createOrder(CreateOrderRequest createOrderRequest) {
        log.info("------------------------POST REQUEST------------------------");
        log.info("Received request to create order");
        orderService.create(orderMapper.toOrder(createOrderRequest));
        log.info("Order created successfully");
        log.info("------------------------POST REQUEST END------------------------");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<FetchOrderResponse> fetchOrder(@PathVariable String id) {
        log.info("------------------------GET REQUEST------------------------");
        log.info("Received request to fetch order with id: {}", id);
        FetchOrderResponse response = orderMapper.toFetchOrderResponse(orderService.fetch(id));
        log.info("Fetched order successfully");
        log.info("------------------------GET REQUEST END------------------------");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateOrderResponse> updateOrder(@PathVariable String id, UpdateOrderRequest updateOrderRequest) {
        log.info("------------------------PUT REQUEST------------------------");
        log.info("Received request to update order with id: {}", id);
        UpdateOrderResponse response = orderMapper.toUpdateOrderResponse(
                orderService.update(id, orderMapper.toOrder(updateOrderRequest)));
        log.info("Order updated successfully");
        log.info("------------------------PUT REQUEST END------------------------");
        return ResponseEntity.ok(response);
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
