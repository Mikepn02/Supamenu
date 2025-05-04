package com.mikepn.supamenubackend.v1.dto.request.order;

import lombok.Data;

import java.util.UUID;


@Data
public class OrderItemDTO {
    private UUID itemId;
    private int quantity;
}
