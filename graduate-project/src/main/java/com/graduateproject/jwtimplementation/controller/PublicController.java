package com.graduateproject.jwtimplementation.controller;

import com.graduateproject.jwtimplementation.model.User;
import com.graduateproject.jwtimplementation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PublicController {

    private final UserService userService;

    @PostMapping("registerUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        var created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
