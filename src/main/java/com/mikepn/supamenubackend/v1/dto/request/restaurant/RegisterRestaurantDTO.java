package com.mikepn.supamenubackend.v1.dto.request.restaurant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mikepn.supamenubackend.v1.enums.ERestaurantType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class RegisterRestaurantDTO {

    private String name;
    private String address;
    private String phoneNumber;
    private String image;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private ERestaurantType type;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openingHours;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingHours;

}
