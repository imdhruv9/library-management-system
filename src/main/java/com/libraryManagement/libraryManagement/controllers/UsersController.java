package com.libraryManagement.libraryManagement.controllers;


import com.libraryManagement.libraryManagement.Entities.Users;
import com.libraryManagement.libraryManagement.model.UpdatePassword;
import com.libraryManagement.libraryManagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
       this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody Users users ){
        try{
        Users users1 =  usersService.save(users);
        return  new ResponseEntity<>(users1, HttpStatus.CREATED);

        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){

        try{
            System.out.println("testion the loggers for logout");
            List<Users> users = usersService.findAll();
            return  new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Users users){
        try{
            Users users1 = usersService.save(users);
            return  new ResponseEntity<>(users1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Optional<Users> users = usersService.getById(id);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUsersByUsername(@PathVariable String username){
        try{
            Users user = usersService.findByUsername(username);
            return new ResponseEntity<>(user,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @PatchMapping("/update/password")
    public ResponseEntity<?> updatePasswordByUsername(@RequestBody UpdatePassword updatePassword){
        try{
            usersService.updatePasswordByUsername(updatePassword);
            return new ResponseEntity<>("password updated successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
