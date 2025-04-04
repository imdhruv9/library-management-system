package com.libraryManagement.libraryManagement.repository;

import com.libraryManagement.libraryManagement.Entities.BookIssueTransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<BookIssueTransactionEntity,Long> {
}
