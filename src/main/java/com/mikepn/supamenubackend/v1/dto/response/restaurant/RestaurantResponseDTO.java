package com.mikepn.supamenubackend.v1.dto.response.restaurant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mikepn.supamenubackend.v1.enums.ERestaurantType;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDTO {

    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String image;
    private String ownerName;
    private ERestaurantType type;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openingHours;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingHours;

}
