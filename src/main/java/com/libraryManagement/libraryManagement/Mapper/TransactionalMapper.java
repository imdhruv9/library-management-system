package com.libraryManagement.libraryManagement.Mapper;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.Entities.BookIssueTransactionEntity;
import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.Entities.Users;
import org.springframework.stereotype.Component;

@Component
public class TransactionalMapper {
    public BookIssueTransactionEntity dtoToBookIssueTransactionEntity (BookInventory bookId , Users userId, Librarian librarianId,Integer quantity){
        return new BookIssueTransactionEntity(bookId ,userId,librarianId,quantity);
    }
}
