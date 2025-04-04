package com.libraryManagement.libraryManagement.services;

import com.libraryManagement.libraryManagement.Entities.Users;
import com.libraryManagement.libraryManagement.model.UpdatePassword;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Users save(Users users);
    List<Users> findAll();
    void update(Users users);
    void delete(Long id);

    Optional<Users> getById(Long id);

    Users findByUsername(String username);
    

    void updatePasswordByUsername(UpdatePassword updatePassword);
}
