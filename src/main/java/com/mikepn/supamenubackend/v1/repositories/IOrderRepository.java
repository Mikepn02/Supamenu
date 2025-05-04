package com.mikepn.supamenubackend.v1.repositories;

import com.mikepn.supamenubackend.v1.models.Order;
import com.mikepn.supamenubackend.v1.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByCustomer_Profile_Email(String email);
    boolean existsByIdAndCustomer_Profile_Email(UUID orderId, String email);
    List<Order> findAllByRestaurant_Name(String name);

    List<Order> findAllByRestaurantId(UUID restaurantId);

}
