package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.Entities.BookTransactionEntity;
import com.libraryManagement.libraryManagement.Entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<BookTransactionEntity,Long> {
    Optional<BookTransactionEntity> findByBookIdAndUserId(BookInventory bookId, Users userId);
    List<BookTransactionEntity> findByBookId(BookInventory book);
    List<BookTransactionEntity> findByUserId(Users user);
}
