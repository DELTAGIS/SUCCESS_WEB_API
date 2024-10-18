package com.deltagis.success.infrastructure.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Clé secrète utilisée pour signer le JWT (assurez-vous de la garder secrète et de la stocker de manière sécurisée)
    private final String secretKey = "secret";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);

            try {
                // Extraction de la clé secrète
                Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

                // Récupération de l'identifiant de l'utilisateur à partir du JWT
                String username = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody()
                        .getSubject();

                if (username != null) {
                    // Créez un objet UserDetails pour représenter l'utilisateur
                    UserDetails userDetails = User.builder()
                            .username(username)
                            .password("")  // Mot de passe vide, car nous ne l'avons pas ici
                            .authorities(Collections.emptyList())
                            .build();

                    // Créez un objet Authentication à partir des UserDetails
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Définit l'objet Authentication dans le contexte de sécurité de Spring
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // Log de l'erreur si la validation du token échoue (token invalide, expiré, etc.)
                e.printStackTrace();
            }
        }

        // Continue avec le filtre suivant dans la chaîne
        filterChain.doFilter(request, response);
    }
}
