package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.model.BookAvailabilityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInventoryRepository extends CrudRepository<BookInventory, Long> {

   List< BookInventory> findByBookNameContainingIgnoreCase(String bookName);

    List<BookInventory> findByAuthorContainingIgnoreCase(String author);
}
