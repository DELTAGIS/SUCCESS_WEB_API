package com.deltagis.success.application.services.user;

import org.springframework.transaction.annotation.Transactional;
import com.deltagis.success.application.services.user.auth.JwtServiceImpl;
import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.ports.user.auth.IUserAuthService;
import com.deltagis.success.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements IUserAuthService {
    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtService;

    private final String SECRET_PASSWORD = "OuJj0qjFQ5596oBYKBGS8GC0aIuAip";

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               AuthenticationManager authenticationManager,
                               UserServiceImpl userService,
                               JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = null;
        this.userService = userService;
        this.jwtService = jwtService;
    }


    public Optional<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::mapToUserDetails);
    }

    /**
     * Converts a User entity to a UserDetails object.
     *
     * @param user the user entity
     * @return a UserDetails instance representing the user
     */
    private UserDetails mapToUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER") // Or use user's actual roles if available
                .build();
    }

    /**
     * Authenticates a user given their username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the authenticated user if the credentials match, or null if they do not
     */
    @Transactional(readOnly = true)
    public String authenticate(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(token);
            User user = userService.getUserByUsername(username);
            UserDetails userDetails = new CustomUserDetails(user);
            return jwtService.generateToken(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves a user to the database, encrypting their password first.
     *
     * @param user the user to be saved
     * @return the saved user
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User registerFirstUser(User admin,String secretKey) {
        // * 1 check if not user exists in the database
        List<User> users = userRepository.findAll();
        if (users.isEmpty() && secretKey.equals(SECRET_PASSWORD)) {
            // * 2 check secret key
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            return userRepository.save(admin);
        }

        return null;
    }
}
