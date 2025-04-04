package com.libraryManagement.libraryManagement.controllers;

import com.libraryManagement.libraryManagement.model.BookIssueTransactionDTO;
import com.libraryManagement.libraryManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class  TransactionController {
    //injecting service layer
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    // api for book issue
    @PostMapping("/issue")
    public ResponseEntity<?> bookIssueController(@RequestBody BookIssueTransactionDTO bookIssueTransactionDTO){
         try{
             transactionService.bookIssueService(bookIssueTransactionDTO);
             return new ResponseEntity<>("book successfully issued", HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>("there is some error please try again after some time",HttpStatus.BAD_GATEWAY);
         }
    }
}
