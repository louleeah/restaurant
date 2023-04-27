package com.training.restaurant.user;

import com.training.restaurant.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Mock
    private UserRepo repo;

    @InjectMocks
    private UserService userService;

    @Test
    void getByCode() {
        User user = new User("ba01234", "Ban Ana");

        when(repo.findByCode("ba01234")).thenReturn(Optional.of(user));

        assertThat(userService.getByCode("ba01234"), equalTo(user));
    }

    @Test
    void getByInvalidCode() {
        Optional<User> user = Optional.empty();

        when(repo.findByCode("ba01234")).thenReturn(user);

        ResourceNotFoundException exc = assertThrows("user not found", ResourceNotFoundException.class,
                () -> userService.getByCode("ba01234"));
        assertEquals("User not found for usercode:: ba01234", exc.getMessage());
    }
}
