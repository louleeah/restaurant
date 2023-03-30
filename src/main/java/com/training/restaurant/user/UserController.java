package com.training.restaurant.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    @Autowired
    private UserInterface service;

    @PostMapping("/users/sign-up")
    public User signUp(@RequestBody User user) {
        return service.createUser(user);
    }
}
