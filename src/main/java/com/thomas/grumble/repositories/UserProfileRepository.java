package com.thomas.grumble.repositories;

import com.thomas.grumble.models.UserEntity;
import com.thomas.grumble.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(UserEntity userEntity);
}
