package com.libraryManagement.libraryManagement.services;

import com.libraryManagement.libraryManagement.model.ActiveBookTransactionDto;
import com.libraryManagement.libraryManagement.model.BookTransactionDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface TransactionService {


    @Transactional
    void bookIssueService(BookTransactionDTO bookTransactionDTO);
    void bookSubmitService(BookTransactionDTO bookTransactionDTO);

    List<ActiveBookTransactionDto> findAllTransactionController();

    List<ActiveBookTransactionDto> findTransactionByBookid(Long bookId);

    List<ActiveBookTransactionDto> findAllTransactionByUserId(Long userId);

    ActiveBookTransactionDto findtransactionByIdController(Long id);
}
