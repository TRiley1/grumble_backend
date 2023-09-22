package com.thomas.grumble.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grumbles")
public class Grumble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties({"grumbles","user", "id","password","likedGrumbles", "dislikedGrumbles"})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
//    @Lob
    @Column(name = "grumble")
    private String grumble;

    @ManyToMany(mappedBy = "likedGrumbles")
    @JsonIgnoreProperties({"password", "roles", "grumbles", "likedGrumbles", "dislikedGrumbles"})// Users who like this grumble
    private List<UserEntity> likingUsers; // Users who like this grumble

    @ManyToMany(mappedBy = "dislikedGrumbles")// Users who dislike this grumble
    @JsonIgnoreProperties({"password", "roles", "grumbles", "likedGrumbles", "dislikedGrumbles"})
    private List<UserEntity> dislikingUsers;

    @Column(name = "approval")
    private String approval;

    public Grumble(UserEntity user, String grumble) {
        this.user = user;
        this.grumble = grumble;
        this.likingUsers = new ArrayList<>();
        this.dislikingUsers = new ArrayList<>();
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getGrumble() {
        return grumble;
    }

    public void setGrumble(String grumble) {
        this.grumble = grumble;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserEntity> getLikingUsers() {
        return likingUsers;
    }

    public void setLikingUsers(List<UserEntity> likingUsers) {
        this.likingUsers = likingUsers;
    }

    public List<UserEntity> getDislikingUsers() {
        return dislikingUsers;
    }

    public void setDislikingUsers(List<UserEntity> dislikingUsers) {
        this.dislikingUsers = dislikingUsers;
    }

    public Grumble() {
    }
}


