package com.thomas.grumble.controllers;

import com.thomas.grumble.models.AvatarConfig;
import com.thomas.grumble.models.UserEntity;
import com.thomas.grumble.models.UserProfile;
import com.thomas.grumble.repositories.UserProfileRepository;
import com.thomas.grumble.repositories.UserRepository;
import com.thomas.grumble.requests.EditProfilePicRequest;
import com.thomas.grumble.requests.UserProjection;
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

        @Autowired
        UserProfileRepository userProfileRepository;

        @GetMapping(value = "/users")
        public ResponseEntity<List<UserEntity>> getAllUsers(
                @RequestParam(name = "user", required = false) String user
        ){
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }

        @GetMapping("/users/{username}")
        public ResponseEntity<UserProjection> getUserById(@PathVariable String username) {
            Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                UserProjection userDTO = new UserProjection(user.getUsername(), user.getId(), user.getGrumbles(), user.getUserProfile(), user.getLikedGrumbles(),user.getDislikedGrumbles());
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/users/{username}")
        public ResponseEntity<?> changeProfilePic(@PathVariable String username, @RequestBody EditProfilePicRequest editProfilePicRequest){
            Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
            UserEntity user = optionalUser.get();
            Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);
            UserProfile userProfile = optionalUserProfile.get();

            AvatarConfig avatarConfig = new AvatarConfig();
            avatarConfig.setAvatarStyle(editProfilePicRequest.getAvatarStyle());
            avatarConfig.setAccessoriesType(editProfilePicRequest.getAccesscoriesType());
            avatarConfig.setTopType(editProfilePicRequest.getHair());
            avatarConfig.setHairColor(editProfilePicRequest.getHairColour());
            avatarConfig.setFacialHairColor(editProfilePicRequest.getFacialHairColour());
            avatarConfig.setFacialHairType(editProfilePicRequest.getFacialHairType());
            avatarConfig.setClotheType(editProfilePicRequest.getClotheType());
            avatarConfig.setClotheColor(editProfilePicRequest.getClotheColour());
            avatarConfig.setEyeType(editProfilePicRequest.getEyeType());
            avatarConfig.setEyebrowType(editProfilePicRequest.getEyebrowType());
            avatarConfig.setMouthType(editProfilePicRequest.getMouthType());
            avatarConfig.setSkinColor(editProfilePicRequest.getSkinColour());

            userProfile.setAvatarConfig(avatarConfig);

            userProfileRepository.save(userProfile);
            userRepository.save(user);

            return new ResponseEntity<>("Successfully edited profile pic!", HttpStatus.OK);

        }

    }

