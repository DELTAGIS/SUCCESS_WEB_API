/**
 * @Author: Eraste e.kouakou@omconsulting-group.com
 * @Date: 2024-10-20 00:14:18
 * @LastEditors: Eraste e.kouakou@omconsulting-group.com
 * @LastEditTime: 2024-10-20 01:12:06
 * @FilePath: src/main/java/com/deltagis/success/application/services/user/auth/CustomUserDetailsService.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package com.deltagis.success.application.services.user.auth;

import com.deltagis.success.application.services.user.UserServiceImpl;
import com.deltagis.success.infrastructure.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = new UserServiceImpl(userRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.deltagis.success.domain.entities.user.User> userSearch = userRepository.findByEmail(email);

        List<String> roles = new ArrayList<>();
        roles.add("USER"); // TODO : get roles from user service

        System.out.println("loadUserByUsername <<" + email + ">> user.get() ==> " + userSearch);
        if (userSearch.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        com.deltagis.success.domain.entities.user.User user = userSearch.get();
        UserDetails userDetails =
                User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}