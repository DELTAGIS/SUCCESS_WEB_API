/**
 * @Author: Eraste e.kouakou@omconsulting-group.com
 * @Date: 2024-10-20 01:25:36
 * @LastEditors: Eraste e.kouakou@omconsulting-group.com
 * @LastEditTime: 2024-10-20 01:26:00
 * @FilePath: src/main/java/com/deltagis/success/domain/security/PasswordManager.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package com.deltagis.success.domain.security;

public interface PasswordManager {
    String ID = PasswordManager.class.getName();

    /**
     * Cryptographically hash a password. Salting (as well as the salt storage
     * scheme) must be handled by the implementation.
     *
     * @param password password to encode.
     * @return the hashed password.
     */
    String encode(String password);

    /**
     * Determines whether the supplied password equals the encoded password or
     * not. Fetching and handling of any required salt value must be done by the
     * implementation.
     *
     * @param rawPassword     the raw, unencoded password.
     * @param encodedPassword the encoded password to match against.
     * @return true if the passwords match, false otherwise.
     */
    boolean matches(String rawPassword, String encodedPassword);

    /**
     * Returns the class name of the password encoder used by this instance.
     *
     * @return the name of the password encoder class.
     */
    String getPasswordEncoderClassName();
}
