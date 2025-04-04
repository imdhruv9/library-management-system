package com.libraryManagement.libraryManagement.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UpdatePassword {
    private String username;

    private String oldPassword;

    private String newPassword;
}
