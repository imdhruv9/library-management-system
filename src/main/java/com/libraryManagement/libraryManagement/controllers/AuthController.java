package com.libraryManagement.libraryManagement.controllers;

import com.libraryManagement.libraryManagement.model.AuthResponse;
import com.libraryManagement.libraryManagement.model.LoginRequest;
import com.libraryManagement.libraryManagement.security.JwtAuthenticationFilter;
import com.libraryManagement.libraryManagement.security.JwtUtil;
import com.libraryManagement.libraryManagement.services.implementations.CustomUserDetailsServiceImpl;
import com.libraryManagement.libraryManagement.services.implementations.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final TokenBlacklistService tokenBlacklistService;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager, CustomUserDetailsServiceImpl customUserDetailsService,
            JwtUtil jwtUtil, JwtAuthenticationFilter jwtAuthenticationFilter, TokenBlacklistService tokenBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.tokenBlacklistService = tokenBlacklistService;

    }


    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequest loginRequest) {
        try {
//            System.out.println(" Login Request : "+loginRequest.getUsername()+", "+loginRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

            String token = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            // Handle invalid username or password
            System.out.println("Invalid credentials: " + e.getMessage());
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.out.println("Exception: " + Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout/{s}")
    public ResponseEntity<?> logoutrequest(@PathVariable String s, HttpServletRequest request) {
        try {
            String token = jwtAuthenticationFilter.extractToken(request);
            System.out.println("your jwt token is " + token);
            long expirationTimeInMillis = 3600 * 1000;
            tokenBlacklistService.blacklistToken(token, expirationTimeInMillis);
            return new ResponseEntity<>("logout successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}





