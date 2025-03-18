package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.repository.LibrarianRepository;
import com.libraryManagement.libraryManagement.services.LibrarianService;
import com.libraryManagement.libraryManagement.utill.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibrarianServiceImpl  implements LibrarianService {

    private  final LibrarianRepository librarianRepository;
    private  final PasswordService passwordService;

    @Autowired
    public LibrarianServiceImpl(LibrarianRepository librarianRepository,PasswordService passwordService){
        this.librarianRepository= librarianRepository;
        this.passwordService = passwordService;
    }


    @Override
    public void save(Librarian librarian){
        try{
            String encodedPassword = passwordService.encodePassword(librarian.getPassword());
            librarian.setPassword(encodedPassword);

         librarianRepository.save(librarian);

        }catch (Exception e){
            throw new RuntimeException("Invalid Data Entry");
        }
    }

    @Override
    public List<Librarian> getAll(){
        try{
        return (List<Librarian>) librarianRepository.findAll();

        }catch (Exception e){
            throw new RuntimeException("No Data Found in the Database");
        }
    }


    @Override
    public Optional<Librarian> getById(Long id){
        try{
        return librarianRepository.findById(id);

        }catch (Exception e){
            throw new RuntimeException("No Id Found");
        }
    }

    @Override
    public void deleteById(Long id){
        try{
        librarianRepository.deleteById(id);

        }catch (Exception e){
            throw new RuntimeException("NO Such Id in the Database");
        }
    }
}
