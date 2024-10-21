/**
 * @Author: Eraste e.kouakou@omconsulting-group.com
 * @Date: 2024-10-20 01:23:34
 * @LastEditors: Eraste e.kouakou@omconsulting-group.com
 * @LastEditTime: 2024-10-20 01:29:44
 * @FilePath: src/main/java/com/deltagis/success/domain/security/SpringSecurityPasswordManager.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package com.deltagis.success.domain.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component()
public class SpringSecurityPasswordManager implements PasswordManager {
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private final PasswordEncoder passwordEncoder;

    public SpringSecurityPasswordManager(PasswordEncoder passwordEncoder) {
        // checkNotNull(passwordEncoder);
        if (passwordEncoder == null) {
            throw new IllegalArgumentException("PasswordEncoder must not be null");
        }

        this.passwordEncoder = passwordEncoder;
    }

    // -------------------------------------------------------------------------
    // PasswordManager implementation
    // -------------------------------------------------------------------------

    @Override
    public final String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public String getPasswordEncoderClassName() {
        return passwordEncoder.getClass().getName();
    }
}
