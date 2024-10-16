package com.deltagis.success.infrastructure.config;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            String username = Jwts.parserBuilder()
                    .setSigningKey("secret")
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            if (username != null) {
                // You can add authentication details here, e.g., set in SecurityContextHolder
                // SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
