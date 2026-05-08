package com.taller.auth.config;

import com.taller.auth.model.Role;
import com.taller.auth.model.User;
import com.taller.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner seedUsers() {
        return args -> {
            if (!userRepository.existsByEmail("admin@taller.com")) {
                User admin = new User();
                admin.setNombre("Admin");
                admin.setEmail("admin@taller.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }
            if (!userRepository.existsByEmail("user@taller.com")) {
                User user = new User();
                user.setNombre("User");
                user.setEmail("user@taller.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                userRepository.save(user);
            }
        };
    }
}
