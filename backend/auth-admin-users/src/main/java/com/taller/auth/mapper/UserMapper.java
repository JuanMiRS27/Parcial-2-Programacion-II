package com.taller.auth.mapper;

import com.taller.auth.dto.response.UserResponse;
import com.taller.auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
