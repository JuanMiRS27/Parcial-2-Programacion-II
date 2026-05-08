package com.taller.auth.service;

import com.taller.auth.dto.request.LoginRequest;
import com.taller.auth.dto.request.RegisterRequest;
import com.taller.auth.dto.response.AuthResponse;
import com.taller.auth.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse me(String email);
}
