package com.deltagis.success.infrastructure.repository;

import com.deltagis.success.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

