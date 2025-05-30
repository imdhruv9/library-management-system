package com.libraryManagement.libraryManagement.controllers;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.model.BookAvailabilityDto;
import com.libraryManagement.libraryManagement.model.UpdateBookQuantity;
import com.libraryManagement.libraryManagement.services.BookInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookInventoryController {
//injecting service layer

    private final BookInventoryService bookInventoryService;

    @Autowired
    public BookInventoryController(BookInventoryService bookInventoryService){
        this.bookInventoryService = bookInventoryService;
    }


    @PostMapping
    public ResponseEntity<?>  createBookInventory(@RequestBody BookInventory bookInventory){
        try{
            bookInventoryService.save(bookInventory);
            return new ResponseEntity<>(bookInventory, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping
    public ResponseEntity<?> getBookInventory(){
        try{
            List<BookInventory> all = bookInventoryService.findAll();
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookInventoryById(@PathVariable Long id) {
        try{
        BookInventory bookInv =  bookInventoryService.findById(id);
        return  new ResponseEntity<>(bookInv, HttpStatus.OK);

        }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping
    public ResponseEntity<?> updateBook( @RequestBody BookInventory bookInventory){
        try{
        BookInventory bookInv =  bookInventoryService.update( bookInventory);
        return new ResponseEntity<>(bookInv,HttpStatus.OK);

        }catch (Exception e){
         return  new ResponseEntity<>("invalid data",HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteById(@PathVariable Long id){
        try{
        bookInventoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }

    @PostMapping("/quantity/update")
    public ResponseEntity<?> updateBookQuantityController( @RequestBody UpdateBookQuantity updateBookQuantity){
        try{
            bookInventoryService.updateBookQuantityService(updateBookQuantity);
            return  new ResponseEntity<>("Book quantity updated successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Book quantity could not be updated for some reasons",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public  ResponseEntity<List<BookAvailabilityDto>> searchBookAvailabilityController
            (@RequestParam(required = false) String bookName, @RequestParam(required = false) String author){
        try{

            List<BookAvailabilityDto> list;
            if(bookName != null) {
                list =
                        bookInventoryService.searchBookAvailibilitybyBookNameService(bookName);
            } else {
                list =
                        bookInventoryService.searchBookAvailibilitybyAutorService(author);
            }
//
            return new ResponseEntity<>(list ,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }

}

