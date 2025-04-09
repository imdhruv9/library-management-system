package com.libraryManagement.libraryManagement.services;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.model.BookAvailabilityDto;
import com.libraryManagement.libraryManagement.model.BookTransactionDTO;
import com.libraryManagement.libraryManagement.model.UpdateBookQuantity;

import java.awt.print.Book;
import java.util.List;

public interface BookInventoryService {

     BookInventory save(BookInventory bookInventory);

    List<BookInventory> findAll();

    BookInventory findById(Long id);

    BookInventory update( BookInventory bookInventory);

    void deleteById(Long id);

    void updateBookQuantityService(UpdateBookQuantity updateBookQuantity);

    List<BookAvailabilityDto> searchBookAvailibilitybyBookNameService(String bookName);

    List<BookAvailabilityDto> searchBookAvailibilitybyAutorService(String author);
}
