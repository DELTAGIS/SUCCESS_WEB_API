package com.deltagis.success.domain.service.user;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the user details if found, or an empty Optional if no such user exists
     */
    Optional<UserDetails> findByUsername(String username);
}