/**
 * @Author: Eraste e.kouakou@omconsulting-group.com
 * @Date: 2024-10-20 01:43:16
 * @LastEditors: Eraste e.kouakou@omconsulting-group.com
 * @LastEditTime: 2024-10-20 01:43:52
 * @FilePath: src/main/java/com/deltagis/success/domain/security/JwtKeyGenerator.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package com.deltagis.success.domain.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64EncodedKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Base64 Encoded Key: " + base64EncodedKey);
    }
}
