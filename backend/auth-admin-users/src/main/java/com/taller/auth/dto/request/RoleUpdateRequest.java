package com.taller.auth.dto.request;

import com.taller.auth.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateRequest {
    @NotNull
    private Role role;
}
