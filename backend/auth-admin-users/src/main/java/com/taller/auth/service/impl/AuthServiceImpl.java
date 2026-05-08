package com.taller.auth.service.impl;

import com.taller.auth.dto.request.LoginRequest;
import com.taller.auth.dto.request.RegisterRequest;
import com.taller.auth.dto.response.AuthResponse;
import com.taller.auth.dto.response.UserResponse;
import com.taller.auth.exception.BadRequestException;
import com.taller.auth.exception.ResourceNotFoundException;
import com.taller.auth.exception.UnauthorizedException;
import com.taller.auth.mapper.UserMapper;
import com.taller.auth.model.Role;
import com.taller.auth.model.User;
import com.taller.auth.repository.UserRepository;
import com.taller.auth.security.JwtService;
import com.taller.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("El email ya existe");
        }
        User user = new User();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Credenciales inválidas"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Credenciales inválidas");
        }
        String token = jwtService.generateToken(user.getId(), user.getEmail(), user.getRole().name());
        return AuthResponse.builder().token(token).user(userMapper.toResponse(user)).build();
    }

    @Override
    public UserResponse me(String email) {
        return userMapper.toResponse(userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
    }
}
