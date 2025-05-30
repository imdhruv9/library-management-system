package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.Librarian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository  extends CrudRepository<Librarian,Long> {
    Librarian findByUsername(String username);
}
