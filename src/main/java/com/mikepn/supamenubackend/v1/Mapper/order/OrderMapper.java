package com.mikepn.supamenubackend.v1.Mapper.order;

import com.mikepn.supamenubackend.v1.dto.response.order.OrderItemDetailDTO;
import com.mikepn.supamenubackend.v1.dto.response.order.OrderResponseDTO;
import com.mikepn.supamenubackend.v1.models.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderResponseDTO toResponseDTO(Order order) {

        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomer().getProfile().getFirstName() + " " + order.getCustomer().getProfile().getLastName())
                .restaurantId(order.getRestaurant().getId())
                .items(order.getItems().stream().map(item -> OrderItemDetailDTO
                        .builder()
                        .itemId(item.getId())
                        .name(item.getItem().getName())
                        .price(item.getPriceAtOrderTime())
                        .quantity(item.getQuantity())
                        .build()).toList())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
