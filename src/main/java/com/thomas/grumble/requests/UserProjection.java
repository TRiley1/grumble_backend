package com.thomas.grumble.requests;

import com.thomas.grumble.models.Grumble;
import com.thomas.grumble.models.UserProfile;
import lombok.Data;

import java.util.List;
@Data
public class UserProjection {
    private String username;
    private Long id;
    private List<Grumble> grumbles;
    private UserProfile userProfile;
    private List<Grumble> likedGrumbles;
    private List<Grumble> dislikedGrumbles;

    public UserProjection(String username, Long id, List<Grumble> grumbles, UserProfile userProfile, List<Grumble> likedGrumbles, List<Grumble> dislikedGrumbles) {
        this.username = username;
        this.id = id;
        this.grumbles = grumbles;
        this.userProfile = userProfile;
        this.likedGrumbles = likedGrumbles;
        this.dislikedGrumbles = dislikedGrumbles;
    }
}
