package com.example.pwa;

import com.example.pwa.Entity.User;
import com.example.pwa.Model.UserModel;
import com.example.pwa.Repository.userRepo;
import com.example.pwa.Services.UserSer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private userRepo userRepo;

    @InjectMocks
    private UserSer userService;

//    @Test
//    void shouldCreateUserSuccessfully() {
//        User mockUser = new User();
//
//        when(userRepo.save(Mockito.any(User.class))).thenReturn(mockUser);
//
//        UserModel m1 = new UserModel(mockUser.getUsername(), mockUser.getPassword(), mockUser.getEmail());
//        UserModel result = userService.createUser(m1);
//        assertEquals("johndoe", result.getUsername());
//    }



    @Test
    void shouldCreateUserSuccessfully() {
        User mockUser = new User();
        mockUser.setUsername("johndoe");
        mockUser.setPassword("password123");
        mockUser.setEmail("john@example.com");

        when(userRepo.save(Mockito.any(User.class))).thenReturn(mockUser);


        UserModel m1 = new UserModel("johndoe", "password123", "john@example.com");
        UserModel result = userService.createUser(m1);

        assertEquals("johndoe", result.getUsername());
        assertEquals("password123", result.getPassword());
        assertEquals("john@example.com", result.getEmail());

    }
}