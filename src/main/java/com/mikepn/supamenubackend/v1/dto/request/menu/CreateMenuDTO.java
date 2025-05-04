package com.mikepn.supamenubackend.v1.dto.request.menu;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateMenuDTO {

    private String name;
    private String description;
    private UUID restaurantId;

}
