package com.mikepn.supamenubackend.v1.dto.request.order;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderDTO {
    private UUID restaurantId;
    private List<OrderItemDTO> items;
}
