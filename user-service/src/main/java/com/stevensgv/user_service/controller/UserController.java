package com.stevensgv.user_service.controller;

import com.stevensgv.user_service.dto.UserAuthDTO;
import com.stevensgv.user_service.mapper.UserMapper;
import com.stevensgv.user_service.model.UserSec;
import com.stevensgv.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserSec>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserAuthDTO> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(userMapper::toAuthDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
