package com.libraryManagement.libraryManagement.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="librarian_details")
@Data

public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="librarian_id")
    private Long id;

    @NotNull
    @Column(name="first_name",nullable = false)
    private String firstName;

    @NotNull
    @Column(name="last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name="username", nullable = false,unique = true)
    private String username;

    @NotNull
    @Column(name="password", nullable = false)
    private String password;

    @NotNull
    @Column(name="date_of_birth", nullable = false)
    private Date dateOfBirth;

    @NotNull
    @Column(name="gender", nullable = false)
    private String gender;

    @NotNull
    @Column(name="phone", nullable = false,unique = true)
    private String phone;

    @NotNull
    @Column(name="email", nullable = false,unique = true)
    private String email;

    @NotNull
    @Column(name="registration_date", nullable = false)
    private Date registrationDate;

    @NotNull
    @Column(name="created_by", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name="created_date", nullable = false)
    private Date createdDate;

    @NotNull
    @Column(name="is_active", nullable = false)
    private Boolean isActive;




}
