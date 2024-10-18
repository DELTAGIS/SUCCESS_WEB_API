package com.deltagis.success.domain.service.user.auth;

import com.deltagis.success.domain.entities.user.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserAuthService {
    /**
     * Authenticates a user by verifying that the provided username and password match an existing user.
     *
     * @param username the username of the user attempting to authenticate
     * @param password the password associated with the given username
     * @return the authenticated User object if the credentials are valid, or null if they are not
     */
    User authenticate(String username, String password);

    /**
     * Saves a user to the database, encrypting their password first.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    User registerUser(User user);



    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return an Optional containing the user if found, or an empty Optional if no such user exists
     */
    Optional<User> findByEmail(String email);

    // TODO  Optional<User> findById(String openId);
}
