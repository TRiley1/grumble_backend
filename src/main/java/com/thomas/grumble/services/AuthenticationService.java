package com.thomas.grumble.services;

import com.thomas.grumble.models.RegistrationRequest;
import com.thomas.grumble.models.UserEntity;
import com.thomas.grumble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest registrationRequest){
    UserEntity user = new UserEntity();
    user.setUsername(registrationRequest.getUsername());
    user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));;

    userRepository.save(user);
    }
}
