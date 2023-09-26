package com.thomas.grumble.models;

import jakarta.persistence.*;
    @Entity
    @Table(name = "user_profiles")
    public class UserProfile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @Embedded
        private AvatarConfig avatarConfig;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public AvatarConfig getAvatarConfig() {
            return avatarConfig;
        }

        public void setAvatarConfig(AvatarConfig avatarConfig) {
            this.avatarConfig = avatarConfig;
        }

        public UserProfile() {
        }
    }


