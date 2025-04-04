package com.libraryManagement.libraryManagement.controllers;

import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.model.UpdatePassword;
import com.libraryManagement.libraryManagement.services.LibrarianService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("librarian")
public class LibrarianController {

    private final LibrarianService librarianService;

    @Autowired
    public LibrarianController(LibrarianService librarianService){
        this.librarianService = librarianService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody Librarian librarian){
        try{
          librarianService.save(librarian);
         return new ResponseEntity<>( HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping
    public  ResponseEntity<?> getAll(){
        try{
            List<Librarian> librarians = (List<Librarian>) librarianService.getAll();
            return  new ResponseEntity<>(librarians, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    @PatchMapping
    public ResponseEntity<?> update(@RequestBody Librarian librarian){
        try{
        librarianService.save(librarian);
        return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLibrarianByid(@PathVariable Long id){
        try{
            Optional<Librarian> librarian = librarianService.getById(id);
            return  new ResponseEntity<>(librarian,HttpStatus.OK);

        }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrarianById(@PathVariable Long id){
        try{
        librarianService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/password/update")
    public ResponseEntity<?> updatePasswordByUsername(@RequestBody UpdatePassword updatePassword){
        try{
            librarianService.updatePasswordByUsername(updatePassword);
            return new ResponseEntity<>("password  Updated Successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("could not update password",HttpStatus.BAD_REQUEST);
        }
    }




}

