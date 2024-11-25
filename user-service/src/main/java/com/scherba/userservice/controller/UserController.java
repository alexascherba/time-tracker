package com.scherba.userservice.controller;

import com.scherba.userservice.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final KeycloakUserService keycloakUserService;

    // Получить список пользователей (только администратор)
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserRepresentation>> getUsers() {
        List<UserRepresentation> users = keycloakUserService.getUsers();
        return ResponseEntity.ok(users);
    }
}
