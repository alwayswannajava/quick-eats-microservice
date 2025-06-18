package com.orderservice.controller.mapper;

import com.orderservice.config.MapperConfig;
import com.orderservice.domain.Order;
import com.orderservice.dto.request.CreateOrderRequest;
import com.orderservice.dto.request.UpdateOrderRequest;
import com.orderservice.dto.response.FetchOrderResponse;
import com.orderservice.dto.response.UpdateOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    void toOrder(@MappingTarget Order existingOrder, Order order);

    Order toOrder(CreateOrderRequest createOrderRequest);

    Order toOrder(UpdateOrderRequest updateOrderRequest);

    FetchOrderResponse toFetchOrderResponse(Order order);

    UpdateOrderResponse toUpdateOrderResponse(Order order);
}
