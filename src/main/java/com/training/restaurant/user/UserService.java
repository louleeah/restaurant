package com.training.restaurant.user;

import com.training.restaurant.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserService implements UserInterface{
    @Autowired
    private UserRepo repo;

    public User createUser(User user) {
        return repo.save(user);
    }

    public User getByCode(String userCode) throws ResourceNotFoundException {
        return repo.findByCode(userCode).orElseThrow(() ->
                new ResourceNotFoundException("User not found for usercode:: " + userCode));
    }
}
