package com.mikepn.supamenubackend.v1.dto.response.order;

import com.mikepn.supamenubackend.v1.enums.EOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private UUID id;
    private String customerName;
    private UUID restaurantId;
    private List<OrderItemDetailDTO> items;
    private BigDecimal totalPrice;
    private EOrderStatus status;
}