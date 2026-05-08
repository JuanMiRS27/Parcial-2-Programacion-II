package com.taller.auth.service.impl;

import com.taller.auth.dto.response.UserResponse;
import com.taller.auth.exception.ResourceNotFoundException;
import com.taller.auth.mapper.UserMapper;
import com.taller.auth.repository.UserRepository;
import com.taller.auth.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper.toResponse(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }
}
