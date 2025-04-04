package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.Entities.BookIssueTransactionEntity;
import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.Entities.Users;
import com.libraryManagement.libraryManagement.Mapper.TransactionalMapper;
import com.libraryManagement.libraryManagement.model.BookIssueTransactionDTO;
import com.libraryManagement.libraryManagement.repository.BookInventoryRepository;
import com.libraryManagement.libraryManagement.repository.LibrarianRepository;
import com.libraryManagement.libraryManagement.repository.TransactionRepository;
import com.libraryManagement.libraryManagement.repository.UsersRepository;
import com.libraryManagement.libraryManagement.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BookInventoryRepository bookInventoryRepository;
    private final UsersRepository usersRepository;
    private final LibrarianRepository librarianRepository;
    private final TransactionalMapper transactionalMapper;
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  BookInventoryRepository bookInventoryRepository,
                                  UsersRepository usersRepository,
                                  LibrarianRepository librarianRepository,
                                  TransactionalMapper transactionalMapper) {
        this.transactionRepository = transactionRepository;
        this.bookInventoryRepository = bookInventoryRepository;
        this.usersRepository = usersRepository;
        this.librarianRepository = librarianRepository;
        this.transactionalMapper = transactionalMapper;
    }

    @Transactional
    @Override
    public void bookIssueService(BookIssueTransactionDTO bookIssueTransactionDTO){
        try {
            BookInventory book = bookInventoryRepository.findById(bookIssueTransactionDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("book Not Found"));
            Users user = usersRepository.findById(bookIssueTransactionDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("user not found"));
            Librarian librarian = librarianRepository.findById(bookIssueTransactionDTO.getLibrarianId())
                    .orElseThrow(() -> new RuntimeException("librarian not found"));

            Integer leftbook = book.getAvailableQuantity() - bookIssueTransactionDTO.getQuantity();
            book.setAvailableQuantity(leftbook);
            bookInventoryRepository.save(book);

            BookIssueTransactionEntity bookIssueTransactionEntity =
                    transactionalMapper.dtoToBookIssueTransactionEntity(book, user, librarian, bookIssueTransactionDTO.getQuantity());

            transactionRepository.save(bookIssueTransactionEntity);
        }catch (Exception e){
            throw new RuntimeException("book could not be issue please try again");
        }


    }
}
