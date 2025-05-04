package com.mikepn.supamenubackend.v1.dto.request.item;

import lombok.Data;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "CreateItemDTO", description = "DTO for creating a new menu item")
public class CreateItemDTO {

    @Schema(description = "Name of the item", example = "Burger", required = true)
    private String name;

    @Schema(description = "Description of the item", example = "Delicious grilled burger", required = true)
    private String description;

    @Schema(description = "Price of the item", example = "12.99", required = true)
    private double price;

    @Schema(description = "Number of items available", example = "50", required = true)
    private int quantityAvailable;

    @Schema(description = "UUID of the menu this item belongs to", example = "3b41c8c7-94ae-4e38-8d0a-bc9f00ddc7e3", required = true)
    private UUID menuId;
}
