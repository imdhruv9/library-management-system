package com.libraryManagement.libraryManagement.security;

import com.libraryManagement.libraryManagement.services.implementations.CustomUserDetailsServiceImpl;
import com.libraryManagement.libraryManagement.services.implementations.TokenBlacklistService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractToken(request);


        if (jwtToken != null) {
            try {

                        // First, check if the token is blacklisted
                        if (tokenBlacklistService.isTokenBlacklisted(jwtToken)) {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.getWriter().write("Token is blacklisted.");
                            return;  // Stop further processing if the token is blacklisted
                        }
                String username = jwtUtil.extractUsername(jwtToken);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(jwtToken, username)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (ExpiredJwtException ex) {
                logger.error("JWT token is expired", ex);
            }
        }
        filterChain.doFilter(request, response);
    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }


}
