package com.mikepn.supamenubackend.v1.services.impl;

import com.mikepn.supamenubackend.v1.Mapper.restaurant.RestaurantMapper;
import com.mikepn.supamenubackend.v1.dto.request.restaurant.RegisterRestaurantDTO;
import com.mikepn.supamenubackend.v1.dto.request.restaurant.UpdateRestaurantDTO;
import com.mikepn.supamenubackend.v1.dto.response.restaurant.RestaurantResponseDTO;
import com.mikepn.supamenubackend.v1.enums.ERestaurantType;
import com.mikepn.supamenubackend.v1.exceptions.AppException;
import com.mikepn.supamenubackend.v1.exceptions.NotFoundException;
import com.mikepn.supamenubackend.v1.models.Restaurant;
import com.mikepn.supamenubackend.v1.repositories.IOrderRepository;
import com.mikepn.supamenubackend.v1.repositories.IRestaurantRepository;
import com.mikepn.supamenubackend.v1.services.IRestaurantService;
import com.mikepn.supamenubackend.v1.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IOrderRepository orderRepository;
    private final RestaurantMapper restaurantMapper;



    @Override
    public RestaurantResponseDTO registerRestaurant(RegisterRestaurantDTO dto) {
        try{
            Restaurant restaurant = Restaurant.builder()
                    .image(dto.getImage())
                    .name(dto.getName())
                    .address(dto.getAddress())
                    .ownerName(dto.getOwnerName())
                    .phoneNumber(dto.getPhoneNumber())
                    .openingHours(dto.getOpeningHours())
                    .closingHours(dto.getClosingHours())
                    .ownerEmail(dto.getOwnerEmail())
                    .ownerPhoneNumber(dto.getOwnerPhoneNumber())
                    .type(dto.getType())
                    .build();

            Restaurant savedRestaurant = restaurantRepository.save(restaurant);

            RestaurantResponseDTO response = restaurantMapper.restaurantResponseDTO(savedRestaurant);


            return response;
        } catch (Exception e) {
            throw  new AppException("Failed to register restaurant: " + e.getMessage());
        }
    }

    @Override
    public RestaurantResponseDTO getRestaurantById(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant Not Found"));
        return Mapper.getMapper().map(restaurant, RestaurantResponseDTO.class);
    }

    @Override
    public RestaurantResponseDTO getRestaurantByName(String name) {
        Restaurant restaurant = restaurantRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Restaurant Not Found"));
        return Mapper.getMapper().map(restaurant, RestaurantResponseDTO.class);

    }

    @Override
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> Mapper.getMapper().map(restaurant , RestaurantResponseDTO.class))
                .toList();
    }

    @Override
    public List<RestaurantResponseDTO> getRestaurantByType(ERestaurantType restaurantType) {
        return restaurantRepository.findByType(restaurantType)
                .stream()
                .map(restaurant -> Mapper.getMapper().map(restaurant , RestaurantResponseDTO.class))
                .toList();
    }

    @Override
    public List<?> getAllOrdersForRestaurant(UUID restaurantId) {
        List<?> orders = orderRepository.findAllByRestaurantId(restaurantId);
        return orders;
    }

    @Override
    public RestaurantResponseDTO updateRestaurant(UUID restaurantId, UpdateRestaurantDTO dto) {
        try{
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new NotFoundException("Restaurant Not Found"));

            if(dto.getName() != null){
                restaurant.setName(dto.getName());
            }
            if(dto.getAddress() != null){
                restaurant.setAddress(dto.getAddress());
            }
            if(dto.getPhoneNumber() != null){
                restaurant.setPhoneNumber(dto.getPhoneNumber());
            }
            if(dto.getOpeningHours() != null){
                restaurant.setOpeningHours(dto.getOpeningHours());
            }
            if(dto.getClosingHours() != null){
                restaurant.setClosingHours(dto.getClosingHours());
            }

            restaurant = restaurantRepository.save(restaurant);
            return restaurantMapper.restaurantResponseDTO(restaurant);
        }catch (Exception e) {
            throw  new AppException("Failed to update restaurant: " + e.getMessage());
        }
    }

    @Override
    public void deleteRestaurant(UUID restaurantId) {
        try{
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new NotFoundException("Restaurant Not Found"));

            restaurantRepository.delete(restaurant);
        }catch (Exception e) {
            throw  new AppException("Failed to delete restaurant: " + e.getMessage());
        }
    }
}
