package com.mikepn.supamenubackend.v1.models;


import com.mikepn.supamenubackend.v1.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "items")
public class Item extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;
    private double price;
    private String image;
    private Boolean isAvailableForPurchase = true;
    private int quantityAvailable = 1;


    @OneToMany
    private List<OrderItem> orderItems;


    @ManyToOne
    @JoinColumn(name = "menu_id" , nullable = false)
    private Menu menu;
}
