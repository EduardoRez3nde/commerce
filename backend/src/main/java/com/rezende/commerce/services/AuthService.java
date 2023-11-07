package com.rezende.commerce.services;

import com.rezende.commerce.entities.User;
import com.rezende.commerce.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateSelfOrAdmin(Long id) {
        User user = userService.authenticated();
        if (!user.hasRole("ROLE_ADMIN") && !user.getId().equals(id)) {
            throw new ForbiddenException("Access denied!");
        }
    }
}
