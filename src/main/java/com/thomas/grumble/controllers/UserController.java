package com.thomas.grumble.controllers;

import com.thomas.grumble.models.UserEntity;
import com.thomas.grumble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
    public class UserController {

        @Autowired
        UserRepository userRepository;

        @GetMapping(value = "/users")
        public ResponseEntity<List<UserEntity>> getAllUsers(
                @RequestParam(name = "user", required = false) String user
        ){
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }

        @GetMapping("/users/{username}")
        public ResponseEntity<UserEntity> getUserById(@PathVariable String username) {
            Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    }

