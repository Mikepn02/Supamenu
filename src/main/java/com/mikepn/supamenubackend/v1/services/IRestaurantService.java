package com.mikepn.supamenubackend.v1.services;

import com.mikepn.supamenubackend.v1.dto.request.restaurant.RegisterRestaurantDTO;
import com.mikepn.supamenubackend.v1.dto.request.restaurant.UpdateRestaurantDTO;
import com.mikepn.supamenubackend.v1.dto.response.restaurant.RestaurantResponseDTO;
import com.mikepn.supamenubackend.v1.enums.ERestaurantType;

import java.util.List;
import java.util.UUID;

public interface IRestaurantService {

    RestaurantResponseDTO registerRestaurant(RegisterRestaurantDTO dto);
    RestaurantResponseDTO getRestaurantById(UUID restaurantId);
    RestaurantResponseDTO getRestaurantByName(String name);
    List<RestaurantResponseDTO> getAllRestaurants();

    List<RestaurantResponseDTO> getRestaurantByType(ERestaurantType restaurantType);

    List<?> getAllOrdersForRestaurant(UUID restaurantId);
    RestaurantResponseDTO updateRestaurant(UUID restaurantId, UpdateRestaurantDTO dto);
    void deleteRestaurant(UUID restaurantId);


}
