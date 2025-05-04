package com.mikepn.supamenubackend.v1.repositories;

import com.mikepn.supamenubackend.v1.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IMenuRepository extends JpaRepository<Menu, UUID> {
    List<Menu> findAllByRestaurant_Id(UUID restaurantId);
}
