package com.libraryManagement.libraryManagement.controllers;

import com.libraryManagement.libraryManagement.model.AuthResponse;
import com.libraryManagement.libraryManagement.model.LoginRequest;
import com.libraryManagement.libraryManagement.security.JwtUtil;
import com.libraryManagement.libraryManagement.services.implementations.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class LoginController {
    private  final AuthenticationManager authenticationManager;
    private  final CustomUserDetailsServiceImpl customUserDetailsService;
    private  final JwtUtil jwtUtil;
    @Autowired
    public LoginController(AuthenticationManager authenticationManager , CustomUserDetailsServiceImpl customUserDetailsService, JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequest loginRequest){
        try{
            System.out.println(" Login Request : "+loginRequest.getUsername()+", "+loginRequest.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        AuthResponse authResponse = new AuthResponse(jwtUtil.generateToken(userDetails.getUsername()));
        return  new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            // Handle invalid username or password
            System.out.println("Invalid credentials: " + e.getMessage());
            return new ResponseEntity<>( "Invalid Credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.out.println("Exception: " + Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }

}
