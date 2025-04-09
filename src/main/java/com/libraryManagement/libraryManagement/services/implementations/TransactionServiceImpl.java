package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.*;
import com.libraryManagement.libraryManagement.Mapper.TransactionalMapper;
import com.libraryManagement.libraryManagement.model.ActiveBookTransactionDto;
import com.libraryManagement.libraryManagement.model.BookTransactionDTO;
import com.libraryManagement.libraryManagement.repository.*;
import com.libraryManagement.libraryManagement.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BookInventoryRepository bookInventoryRepository;
    private final UsersRepository usersRepository;
    private final LibrarianRepository librarianRepository;
    private final TransactionalMapper transactionalMapper;
    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  BookInventoryRepository bookInventoryRepository,
                                  UsersRepository usersRepository,
                                  LibrarianRepository librarianRepository,
                                  TransactionalMapper transactionalMapper,
                                  BookTransactionHistoryRepository bookTransactionHistoryRepository) {
        this.transactionRepository = transactionRepository;
        this.bookInventoryRepository = bookInventoryRepository;
        this.usersRepository = usersRepository;
        this.librarianRepository = librarianRepository;
        this.transactionalMapper = transactionalMapper;
        this.bookTransactionHistoryRepository = bookTransactionHistoryRepository;
    }

    @Transactional
    @Override
    public void bookIssueService(BookTransactionDTO bookTransactionDTO){
        try {
            BookInventory book = bookInventoryRepository.findById(bookTransactionDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("book Not Found"));
            Users user = usersRepository.findById(bookTransactionDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("user not found"));
            Librarian librarian = librarianRepository.findById(bookTransactionDTO.getLibrarianId())
                    .orElseThrow(() -> new RuntimeException("librarian not found"));

            Integer leftBook = book.getAvailableQuantity();
            leftBook--;
            book.setAvailableQuantity(leftBook);
            bookInventoryRepository.save(book);

            BookTransactionEntity bookTransactionEntity =
                    transactionalMapper.dtoToBookIssueTransactionEntity(book, user, librarian);

            transactionRepository.save(bookTransactionEntity);
        }catch (Exception e){
            throw new RuntimeException("book could not be issue please try again");
        }
    }


    @Transactional
    @Override
    public void bookSubmitService(BookTransactionDTO bookTransactionDTO){
        try {
            BookInventory book = bookInventoryRepository.findById(bookTransactionDTO.getBookId())
                    .orElseThrow(() -> new RuntimeException("book Not Found"));
            Users user = usersRepository.findById(bookTransactionDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("user not found"));

            Integer leftbook = book.getAvailableQuantity();
            leftbook++;
            book.setAvailableQuantity(leftbook);
            bookInventoryRepository.save(book);
            LocalDate submitDate = LocalDate.now();
            BookTransactionEntity bookTransaction =
                    transactionRepository.findByBookIdAndUserId(book, user)
                            .orElseThrow(() -> new RuntimeException("transaction now found"));
            bookTransaction.setSubmitDate(submitDate);
            BookTransactionHistory bookTransactionHistory = new BookTransactionHistory(bookTransaction);
            bookTransactionHistoryRepository.save(bookTransactionHistory);

            transactionRepository.delete(bookTransaction);
        }catch (Exception e){
            throw new RuntimeException("book could not be submitted database failure");
        }
    }

    @Override
    public List<ActiveBookTransactionDto> findAllTransactionController(){
        try {
            Iterable<BookTransactionEntity> allTransaction = transactionRepository.findAll();

            List<BookTransactionEntity> list = new ArrayList<>();
            allTransaction.forEach(list::add);

            return getActiveBookTransactionDtos(list);
        }catch (Exception e){
            throw new RuntimeException("transaction could not be fetched",e);
        }
    }

    @Override
    public List<ActiveBookTransactionDto> findTransactionByBookid(Long bookId){
        try {
            Optional<BookInventory> optionalbook = bookInventoryRepository.findById(bookId);
            BookInventory book = optionalbook.orElseThrow((() -> new RuntimeException("it is empty")));
            List<BookTransactionEntity> allTransaction = transactionRepository.findByBookId(book);

            return getActiveBookTransactionDtos(allTransaction);
        }catch (Exception e){
             throw new RuntimeException("transaction could not be fetch",e);
        }
    }

    @Override
    public List<ActiveBookTransactionDto> findAllTransactionByUserId(Long userId){
        try{
            Optional<Users> optionalUsers = usersRepository.findById(userId);
            Users user = optionalUsers.orElseThrow(()->new RuntimeException("user could not found"));
            List<BookTransactionEntity> allTransaction = transactionRepository.findByUserId(user);
            return getActiveBookTransactionDtos(allTransaction);
        }catch (Exception e){
            throw new RuntimeException("transaction could not be fetched",e);
        }
    }
    @Override
    public ActiveBookTransactionDto findtransactionByIdController(Long id){

    try {
        Optional<BookTransactionEntity> bookTransactionOptional = transactionRepository.findById(id);
        Optional<BookTransactionHistory> bookTransactionHistoryOptional = bookTransactionHistoryRepository.findById(id);
        if (bookTransactionOptional.isPresent()) {
            BookTransactionEntity transaction =
                    bookTransactionOptional.orElseThrow(() -> new RuntimeException("transaction not found"));
            return new ActiveBookTransactionDto
                    (transaction.getTransactionId(), transaction.getBookId().getId(), transaction.getBookId().getBookName(),
                            transaction.getUserId().getId(), transaction.getLibrarianId().getId(), transaction.getIssuedDate(),
                            transaction.getDueDate(),transaction.getSubmitDate());
        } else {
            BookTransactionHistory bookTransactionHistory =
                    bookTransactionHistoryOptional.orElseThrow(() -> new RuntimeException(" transaction not found"));
            return new ActiveBookTransactionDto
                    (bookTransactionHistory.getTransactionId(), bookTransactionHistory.getBookId().getId(),
                            bookTransactionHistory.getBookId().getBookName(), bookTransactionHistory.getUserId().getId(),
                            bookTransactionHistory.getLibrarianId().getId(), bookTransactionHistory.getIssuedDate(),
                            bookTransactionHistory.getDueDate(),bookTransactionHistory.getSubmitDate());
        }
    }catch (Exception e){
    throw new RuntimeException("transaction not found");
    }
    }


    private static List<ActiveBookTransactionDto> getActiveBookTransactionDtos(List<BookTransactionEntity> allTransaction) {
        List<ActiveBookTransactionDto> activeBookTransactionDtoList = new ArrayList<>();
        for (BookTransactionEntity transaction : allTransaction) {
            ActiveBookTransactionDto activeBookTransactionDto = new ActiveBookTransactionDto
                    (transaction.getTransactionId(), transaction.getBookId().getId(), transaction.getBookId().getBookName(),
                            transaction.getUserId().getId(), transaction.getLibrarianId().getId(), transaction.getIssuedDate(),
                            transaction.getDueDate(),transaction.getSubmitDate());
            activeBookTransactionDtoList.add(activeBookTransactionDto);
        }
        return activeBookTransactionDtoList;
    }
}
