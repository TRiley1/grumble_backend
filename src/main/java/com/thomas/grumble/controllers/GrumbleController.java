package com.thomas.grumble.controllers;

import com.thomas.grumble.models.*;
import com.thomas.grumble.repositories.GrumbleRepository;
import com.thomas.grumble.repositories.UserRepository;
import com.thomas.grumble.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GrumbleController {
    @Autowired
    GrumbleRepository grumbleRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/grumbles/like")
    public ResponseEntity<?> likePost(@RequestBody LikeRequest likeRequest) {
        Long grumbleId = likeRequest.getId();
        String username = likeRequest.getUsername();

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        Optional<Grumble> optionalGrumble = grumbleRepository.findById(grumbleId);

        if (optionalUserEntity.isEmpty() || optionalGrumble.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Grumble not found");
        }

        Grumble grumble = optionalGrumble.get();
        UserEntity user = optionalUserEntity.get();

        if (!hasLiked(grumble, user)) {
            grumble.getLikingUsers().add(user);
            user.getLikedGrumbles().add(grumble);
            grumbleRepository.save(grumble);
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Liked the Grumble"));
        } else {
            return ResponseEntity.badRequest().body("You've already liked this Grumble.");
        }
    }


    @PostMapping(value = "/grumbles/dislike")
    public ResponseEntity<?> dislikePost(@RequestBody DislikeRequest dislikeRequest) {
        Long grumbleId = dislikeRequest.getId();
        String username = dislikeRequest.getUsername();

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        Optional<Grumble> optionalGrumble = grumbleRepository.findById(grumbleId);

        if (optionalUserEntity.isEmpty() || optionalGrumble.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Grumble not found");
        }

        Grumble grumble = optionalGrumble.get();
        UserEntity user = optionalUserEntity.get();

        if (!hasDisliked(grumble, user)) {
            grumble.getDislikingUsers().add(user);
            user.getDislikedGrumbles().add(grumble);
            grumbleRepository.save(grumble);
            return ResponseEntity.ok(Map.of("message", "DisLiked the Grumble"));
        } else {
            return ResponseEntity.badRequest().body("You've already disliked this Grumble.");
        }
    }

    private boolean hasLiked(Grumble grumble, UserEntity user) {
        return grumble.getLikingUsers().contains(user);
    }

    private boolean hasDisliked(Grumble grumble, UserEntity user) {
        return grumble.getDislikingUsers().contains(user);
    }



    @PostMapping(value = "/grumbles/add")
    public ResponseEntity<?> createPost(@RequestBody AddRequest addRequest) {

        Optional<UserEntity> userOptional = userRepository.findByUsername(addRequest.getUsername());

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            Grumble grumble = new Grumble();
            grumble.setUser(user);
            grumble.setGrumble(addRequest.getContent());

            grumble.setApproval("Pending Approval");
            grumbleRepository.save(grumble);

            return ResponseEntity.ok("Grumble created successfully.");
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

    }

    @GetMapping(value = "/grumbles")
    public ResponseEntity<List<Grumble>> getAllGrumbles(
            @RequestParam(name = "grumbles", required = false) String grumbles
    ){
        return new ResponseEntity<>(grumbleRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "grumbles/verdict")
    public ResponseEntity<?>grumbleVerdict(@RequestBody VerdictRequest verdictRequest){

        Optional<Grumble> grumbleOptional = grumbleRepository.findById(verdictRequest.getGrumbleID());

        if(grumbleOptional.isEmpty()){
            return new ResponseEntity<>("This grumble doesn't exist",HttpStatus.NOT_FOUND);
        }

       Grumble grumble = grumbleOptional.get();
       grumble.setApproval(verdictRequest.getVerdict());
       grumbleRepository.save(grumble);
       return new ResponseEntity<>("You have been judged",HttpStatus.OK);
    }

}

