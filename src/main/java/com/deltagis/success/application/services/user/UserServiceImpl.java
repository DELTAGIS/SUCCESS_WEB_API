package com.deltagis.success.application.services.user;

import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.ports.user.IUserService;
import com.deltagis.success.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to find
     * @return the user associated with the given username, or null if no such user exists
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
