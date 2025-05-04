package com.mikepn.supamenubackend.v1.dto.request.auth;

import com.mikepn.supamenubackend.v1.annotations.ValidPassword;
import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String oldPassword;
    @ValidPassword(message = "Password should be strong")
    private String newPassword;
}