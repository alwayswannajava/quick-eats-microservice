package com.orderservice.controller.mapper;

import com.orderservice.config.MapperConfig;
import com.orderservice.domain.Order;
import com.orderservice.dto.request.CreateOrderRequest;
import com.orderservice.dto.request.UpdateOrderRequest;
import com.orderservice.dto.response.FetchOrderResponse;
import com.orderservice.dto.response.UpdateOrderResponse;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface OrderMapper {

    Order toOrder(CreateOrderRequest createOrderRequest);

    Order toOrder(UpdateOrderRequest updateOrderRequest);

    FetchOrderResponse toFetchOrderResponse(Order order);

    UpdateOrderResponse toUpdateOrderResponse(Order order);
}
