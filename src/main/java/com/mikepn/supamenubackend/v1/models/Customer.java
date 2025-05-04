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

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @OneToOne
    @JoinColumn(name = "profile_id")
    private User profile;


    @OneToMany(mappedBy = "customer")
    private List<Order> order;
}
