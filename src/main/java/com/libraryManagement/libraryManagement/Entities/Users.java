package com.libraryManagement.libraryManagement.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name ="users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="registration_date")
    private Date registrationDate;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="gender")
    private String gender;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="is_active")
    private Boolean isActive;


}
