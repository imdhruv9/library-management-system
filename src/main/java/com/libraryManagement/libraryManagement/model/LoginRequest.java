package com.libraryManagement.libraryManagement.model;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class LoginRequest {

    private String username;

    private String password;
}
