package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.Librarian;
import com.libraryManagement.libraryManagement.Entities.Users;
import com.libraryManagement.libraryManagement.repository.LibrarianRepository;
import com.libraryManagement.libraryManagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    private  final LibrarianRepository librarianRepository;


    @Autowired
    public CustomUserDetailsServiceImpl(UsersRepository usersRepository,LibrarianRepository librarianRepository) {
        this.usersRepository = usersRepository;
        this.librarianRepository = librarianRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
            Librarian librarian = librarianRepository.findByUsername(username);


        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());

        }
        if(librarian != null){
            return new org.springframework.security.core.userdetails.User(librarian.getUsername(), librarian.getPassword(), Collections.emptyList());

        }

        throw new UsernameNotFoundException("User Not Found For UserName : "+username);
    }
}
