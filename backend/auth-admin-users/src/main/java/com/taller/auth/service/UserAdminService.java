package com.taller.auth.service;

import com.taller.auth.dto.response.UserResponse;

import java.util.List;

public interface UserAdminService {
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    void deleteById(Long id);
}
