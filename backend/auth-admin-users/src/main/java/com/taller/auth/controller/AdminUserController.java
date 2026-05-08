package com.taller.auth.controller;

import com.taller.auth.dto.request.RoleUpdateRequest;
import com.taller.auth.dto.response.UserResponse;
import com.taller.auth.service.UserAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserAdminService userAdminService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userAdminService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userAdminService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userAdminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/role")
    public UserResponse updateRole(@PathVariable Long id, @Valid @RequestBody RoleUpdateRequest request) {
        return userAdminService.updateRole(id, request.getRole());
    }
}
