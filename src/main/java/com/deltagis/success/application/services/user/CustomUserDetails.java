package com.deltagis.success.application.services.user;

import com.deltagis.success.domain.entities.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User user;

    // Constructor accepting your custom domain User class
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // TODO : Change based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // TODO : Change based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // TODO : Change based on your requirements
    }

    @Override
    public boolean isEnabled() {
        return true;  // TODO : Change based on your requirements
    }

    /**
     * @return The user associated with this {@link UserDetails} instance.
     */
    public User getUser() {
        return user;
    }
}
