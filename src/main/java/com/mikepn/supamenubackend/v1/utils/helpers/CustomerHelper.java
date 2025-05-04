package com.mikepn.supamenubackend.v1.utils.helpers;

import com.mikepn.supamenubackend.v1.dto.request.customer.CreateCustomerDTO;
import com.mikepn.supamenubackend.v1.models.Customer;
import com.mikepn.supamenubackend.v1.models.Role;
import com.mikepn.supamenubackend.v1.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomerHelper {

    public User buildUserFromDTO(CreateCustomerDTO dto, Role role, PasswordEncoder encoder) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fullName(dto.getFirstName() + " " + dto.getLastName())
                .email(dto.getEmail())
                .dob(dto.getDateOfBirth())
                .password(encoder.encode(dto.getPassword()))
                .roles(Set.of(role))
                .build();
    }

    public Customer buildCustomer(User user) {
        return Customer.builder()
                .profile(user)
                .build();
    }


}
