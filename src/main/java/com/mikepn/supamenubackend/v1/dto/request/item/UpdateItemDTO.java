package com.mikepn.supamenubackend.v1.dto.request.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateItemDTO {

    private String name;
    private String description;
    private double price;
    private int quantityAvailable;
}
