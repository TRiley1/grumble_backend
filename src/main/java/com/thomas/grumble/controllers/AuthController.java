package com.thomas.grumble.controllers;

import com.thomas.grumble.models.*;
import com.thomas.grumble.repositories.RoleRepository;
import com.thomas.grumble.repositories.UserProfileRepository;
import com.thomas.grumble.repositories.UserRepository;
import com.thomas.grumble.requests.LoginRequest;
import com.thomas.grumble.requests.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserProfileRepository userProfileRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator tokenGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtGenerator tokenGenerator, UserProfileRepository userProfileRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.userProfileRepository = userProfileRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        // Create a new UserProfile
        UserProfile userProfile = new UserProfile();

        // Set default values for AvatarConfig properties
        AvatarConfig avatarConfig = new AvatarConfig();
        avatarConfig.setAvatarStyle("Circle");
        avatarConfig.setAccessoriesType("Blank");
        avatarConfig.setTopType("ShortHairShortCurly");
        avatarConfig.setHairColor("Black");
        avatarConfig.setFacialHairColor("Black");
        avatarConfig.setFacialHairType("Blank");
        avatarConfig.setClotheType("CollarSweater");
        avatarConfig.setClotheColor("Red");
        avatarConfig.setEyeType("Default");
        avatarConfig.setEyebrowType("Default");
        avatarConfig.setMouthType("Default");
        avatarConfig.setSkinColor("Light");

        // Associate the AvatarConfig with the UserProfile
        userProfile.setAvatarConfig(avatarConfig);

        // Save the UserProfile
        userProfileRepository.save(userProfile);

        // Create a new UserEntity and set its properties
        UserEntity user = new UserEntity();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // Set the UserEntity and UserProfile associations bidirectionally
        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        // Set user roles
        Roles roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        // Save the UserEntity (which includes the UserProfile due to cascading)
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
