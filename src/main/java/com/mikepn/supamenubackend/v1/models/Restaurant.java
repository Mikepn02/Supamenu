package com.mikepn.supamenubackend.v1.models;


import com.mikepn.supamenubackend.v1.common.AbstractEntity;
import com.mikepn.supamenubackend.v1.enums.ERestaurantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true , nullable = false)
    private String name;
    private String address;
    private String phoneNumber;
    private String image;
    private float rating = 0;

    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;

    private LocalTime openingHours;
    private LocalTime closingHours;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ERestaurantType type;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;


    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;




}
