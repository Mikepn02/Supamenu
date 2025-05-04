package com.mikepn.supamenubackend.v1.Mapper.restaurant;

import com.mikepn.supamenubackend.v1.dto.response.restaurant.RestaurantResponseDTO;
import com.mikepn.supamenubackend.v1.models.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {

    public RestaurantResponseDTO restaurantResponseDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .image(restaurant.getImage())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .ownerName(restaurant.getOwnerName())
                .type(restaurant.getType())
                .openingHours(restaurant.getOpeningHours())
                .closingHours(restaurant.getClosingHours())
                .build();
    }
}
