package com.libraryManagement.libraryManagement.services;

import com.libraryManagement.libraryManagement.Entities.BookInventory;

import java.awt.print.Book;
import java.util.List;

public interface BookInventoryService {

     BookInventory save(BookInventory bookInventory);

    List<BookInventory> findAll();

    BookInventory findById(Long id);

    BookInventory update( BookInventory bookInventory);

    void deleteById(Long id);
}
