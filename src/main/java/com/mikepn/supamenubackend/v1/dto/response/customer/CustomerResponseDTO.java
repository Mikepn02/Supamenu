package com.mikepn.supamenubackend.v1.dto.response.customer;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private UUID id;
    private String fullName;
    private String email;
    private String dob;
    private boolean verified;
    private String accountNumber;
    private double balance;


}