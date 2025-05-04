package com.mikepn.supamenubackend.v1.repositories;

import com.mikepn.supamenubackend.v1.enums.ERestaurantType;
import com.mikepn.supamenubackend.v1.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRestaurantRepository extends JpaRepository<Restaurant, UUID> {

    Optional<Restaurant> findByName(String name);

    List<Restaurant> findByType(ERestaurantType type);

}
