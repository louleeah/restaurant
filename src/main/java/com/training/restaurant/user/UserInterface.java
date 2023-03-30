package com.training.restaurant.user;

import com.training.restaurant.ResourceNotFoundException;

public interface UserInterface {
    User createUser(User user);

    User getByCode(String userCode) throws ResourceNotFoundException;
}
