package com.mikepn.supamenubackend.v1.dto.request.restaurant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
public class UpdateRestaurantDTO {

    private String name;
    private String address;
    private String phoneNumber;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openingHours;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingHours;
}
