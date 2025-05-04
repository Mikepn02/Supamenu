package com.mikepn.supamenubackend.v1.dto.request.role;

import com.mikepn.supamenubackend.v1.enums.ERole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateRoleDTO {
    @Schema(example = "ADMIN", description = "Role name")
    private ERole name;
}
