package com.stevensgv.auth_service.controller;

import com.stevensgv.auth_service.model.UserSec;
import com.stevensgv.auth_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_USER')")
    public ResponseEntity<List<UserSec>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_USER')")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
