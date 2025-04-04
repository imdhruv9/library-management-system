package com.libraryManagement.libraryManagement.services;

import com.libraryManagement.libraryManagement.Entities.BookIssueTransactionEntity;
import com.libraryManagement.libraryManagement.model.BookIssueTransactionDTO;
import jakarta.transaction.Transactional;

public interface TransactionService {


    @Transactional
    void bookIssueService(BookIssueTransactionDTO bookIssueTransactionDTO);
}
