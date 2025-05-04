package com.mikepn.supamenubackend.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikepn.supamenubackend.v1.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ERole roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;
}

