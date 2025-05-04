package com.mikepn.supamenubackend.v1.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDetailDTO {
    private UUID itemId;
    private String name;
    private int quantity;
    private double price;
}