package com.deltagis.success.domain.service.user;


import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.infrastructure.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return an Optional containing the user if found, or an empty Optional if no such user exists
     */
    public Optional<UserDetails> findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .map(CustomUserDetails::new);
    }

    /**
     * Saves a user to the database, encrypting their password first.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}