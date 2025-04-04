package com.libraryManagement.libraryManagement.services;

import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.model.UpdatePassword;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LibrarianService  {
    void save(Librarian librarian);

    List<Librarian> getAll();

    Optional<Librarian> getById(Long id);

    void deleteById(Long id);

    void updatePasswordByUsername(UpdatePassword updatePassword);
}
