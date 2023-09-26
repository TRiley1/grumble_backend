package com.thomas.grumble.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user", "id","password"})
    private List<Grumble> grumbles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Roles> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private UserProfile userProfile;


    @ManyToMany
    @JsonIgnoreProperties({"likingUsers", "dislikingUsers"})
    @JoinTable(
            name = "user_liked_grumbles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "grumble_id")
    )
    private List<Grumble> likedGrumbles;

    @ManyToMany
    @JsonIgnoreProperties({"likingUsers", "dislikingUsers"})
    @JoinTable(
            name = "user_disliked_grumbles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "grumble_id")
    )
    private List<Grumble> dislikedGrumbles;

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }



    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.grumbles = new ArrayList<>();
    }

    public List<Grumble> getGrumbles() {
        return grumbles;
    }

    public void setGrumbles(List<Grumble> grumbles) {
        this.grumbles = grumbles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Grumble> getLikedGrumbles() {
        return likedGrumbles;
    }

    public void setLikedGrumbles(List<Grumble> likedGrumbles) {
        this.likedGrumbles = likedGrumbles;
    }

    public List<Grumble> getDislikedGrumbles() {
        return dislikedGrumbles;
    }

    public void setDislikedGrumbles(List<Grumble> dislikedGrumbles) {
        this.dislikedGrumbles = dislikedGrumbles;
    }

    public UserEntity() {
    }
}
