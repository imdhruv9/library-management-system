package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
    Users findByUsername(String username);

}
