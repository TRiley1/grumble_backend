package com.thomas.grumble.repositories;

import com.thomas.grumble.models.Grumble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrumbleRepository extends JpaRepository<Grumble, Long> {
    Optional<Grumble> findById(Long id);
}
