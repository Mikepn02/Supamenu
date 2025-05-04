package com.mikepn.supamenubackend.v1.repositories;

import com.mikepn.supamenubackend.v1.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITemRepository extends JpaRepository<Item, UUID> {

    List<Item> findAllByName(String name);
    List<Item> findAllByMenu_Id(UUID id);
}
