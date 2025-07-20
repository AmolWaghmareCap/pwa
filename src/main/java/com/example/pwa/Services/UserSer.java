package com.example.pwa.Services;

import com.example.pwa.Entity.User;
import com.example.pwa.Exception.CustomException;
import com.example.pwa.Model.UserModel;
import com.example.pwa.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSer {

    @Autowired
    private userRepo userRepository;

    public UserModel createUser(UserModel userDto) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new CustomException("Email already registered", HttpStatus.CONFLICT);
        }

        // Map DTO to entity
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // You should hash this

        User savedUser = userRepository.save(user);

        // Convert to response DTO
        UserModel response = new UserModel();
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        return response;
    }


    public UserModel getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        UserModel response = new UserModel();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }

}
