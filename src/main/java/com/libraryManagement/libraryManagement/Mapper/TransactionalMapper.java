package com.libraryManagement.libraryManagement.Mapper;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.Entities.BookTransactionEntity;
import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.Entities.Users;
import org.springframework.stereotype.Component;

@Component
public class TransactionalMapper {
    public BookTransactionEntity dtoToBookIssueTransactionEntity (BookInventory bookId , Users userId, Librarian librarianId){
        return new BookTransactionEntity(bookId ,userId,librarianId);
    }
}
