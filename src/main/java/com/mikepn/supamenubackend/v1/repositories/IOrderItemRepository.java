package com.mikepn.supamenubackend.v1.repositories;

import com.mikepn.supamenubackend.v1.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
