package com.libraryManagement.libraryManagement.controllers;

import com.libraryManagement.libraryManagement.model.ActiveBookTransactionDto;
import com.libraryManagement.libraryManagement.model.BookTransactionDTO;
import com.libraryManagement.libraryManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> bookIssueController(@RequestBody BookTransactionDTO bookTransactionDTO){
         try{
             transactionService.bookIssueService(bookTransactionDTO);
             return new ResponseEntity<>("book successfully issued", HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>("there is some error please try again after some time",HttpStatus.BAD_GATEWAY);
         }
    }
    @PostMapping("/submit")
    public ResponseEntity<?> bookSubmitController(@RequestBody BookTransactionDTO bookTransactionDTO){
        try{
            transactionService.bookSubmitService(bookTransactionDTO);
            return new ResponseEntity<>("book Successfully Submitted",HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("book could not be submitted",HttpStatus.NOT_FOUND);
        }
    }


   @GetMapping("/active")
    public ResponseEntity<?> activeTransactionController(){
        try {
            List<ActiveBookTransactionDto> list = transactionService.findAllTransactionController();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("no record found",HttpStatus.NOT_FOUND);
        }
   }
   @GetMapping("/active/book/{bookId}")
    public ResponseEntity<?> activeTransactionControllerByBookId(@PathVariable Long bookId){
        try{
            List<ActiveBookTransactionDto> list = transactionService.findTransactionByBookid(bookId);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("transaction could not be fetched",HttpStatus.NOT_FOUND);
        }
   }


   @GetMapping("/active/user/{userId}")
    public ResponseEntity<?> activeTransactionControllerByUserid(@PathVariable Long userId){
        try{
            List<ActiveBookTransactionDto> list = transactionService.findAllTransactionByUserId(userId);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("no transaction found with this user",HttpStatus.NOT_FOUND);
        }
   }


   @GetMapping("/active/{id}")
    public ResponseEntity<?> activeTransactionControllerById(@PathVariable Long id){
        try{
            ActiveBookTransactionDto transactionDto = transactionService.findtransactionByIdController(id);
            return  new ResponseEntity<>(transactionDto,HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>("no transaction found",HttpStatus.NOT_FOUND);
        }
   }



}
