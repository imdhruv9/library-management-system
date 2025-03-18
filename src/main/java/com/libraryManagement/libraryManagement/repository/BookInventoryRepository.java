package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInventoryRepository extends CrudRepository<BookInventory, Long> {

}
