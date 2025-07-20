package com.example.pwa.Controller;

import com.example.pwa.Model.UserModel;
import com.example.pwa.Services.UserSer;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basePath/v1/users/user")
public class Usercontroller {

    @Autowired
    private UserSer user;

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel dto) {
        UserModel response = user.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
