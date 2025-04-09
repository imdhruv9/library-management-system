package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.BookTransactionHistory;
import org.springframework.data.repository.CrudRepository;

public interface BookTransactionHistoryRepository extends CrudRepository<BookTransactionHistory,Long> {
}
