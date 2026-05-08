package com.taller.auth.dto.response;

import com.taller.auth.model.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String nombre;
    private String email;
    private Role role;
}
