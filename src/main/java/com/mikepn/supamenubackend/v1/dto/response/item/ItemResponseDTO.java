package com.mikepn.supamenubackend.v1.dto.response.item;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int quantityAvailable;
}
