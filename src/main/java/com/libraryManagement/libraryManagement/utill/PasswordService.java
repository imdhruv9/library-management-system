package com.libraryManagement.libraryManagement.utill;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    public String encodePassword(String rawPassword){
        return bCryptPasswordEncoder.encode(rawPassword);
    }
}
