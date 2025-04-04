package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.Users;
import com.libraryManagement.libraryManagement.model.UpdatePassword;
import com.libraryManagement.libraryManagement.repository.UsersRepository;
import com.libraryManagement.libraryManagement.services.UsersService;
import com.libraryManagement.libraryManagement.utill.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordService passwordService;

    @Autowired
     public UsersServiceImpl(UsersRepository usersRepository, PasswordService passwordService){
        this.usersRepository = usersRepository;
        this.passwordService =  passwordService;
    }


    @Override
    public Users save(Users users){
        try{
            log.info("Attempting to save user: {}", users);
            String encodedPassword = passwordService.encodePassword(users.getPassword());
            users.setPassword(encodedPassword);
        return usersRepository.save(users);

        }catch (Exception e){
            log.error("duplicate entry not allowed",e);
            throw new RuntimeException("Invalid Data Entry");
        }
    }


    @Override
    public List<Users> findAll(){
        try{
        return (List<Users>)usersRepository.findAll();

        }catch (Exception e){
            throw new RuntimeException("No Data Found");
        }
    }

    @Override
    public void update(Users users){
        try{
            String  encodedPassword = passwordService.encodePassword(users.getPassword()) ;
            users.setPassword(encodedPassword);
        usersRepository.save(users);

        }catch (Exception e){
            throw new RuntimeException("No Such Data Found");
        }
    }

    @Override
    public void delete(Long id){
        try{
        usersRepository.deleteById(id);

        }catch (Exception e){
            log.error("no such user found in the database please check your id ", e);
            throw new RuntimeException("No Data Found in the Database");
        }
    }


    @Override
    public Optional<Users> getById(Long id){
        try{
        return usersRepository.findById(id);


        }catch (Exception e){
            throw new RuntimeException("No Such User Found ");
        }
    }
    @Override
    public Users findByUsername(String username){
        try{
            return usersRepository.findByUsername(username);

        }catch (Exception e){
            throw new RuntimeException("no Such User Found");
        }
    }
    @Override
    public void updatePasswordByUsername(UpdatePassword updatePassword){
        try {
            Users users = usersRepository.findByUsername(updatePassword.getUsername());


            if (passwordService.passwordMatcher(updatePassword.getOldPassword(),users.getPassword())) {
                String newEncodedPassword = passwordService.encodePassword(updatePassword.getNewPassword());
                users.setPassword(newEncodedPassword);
                usersRepository.save(users);
            }
             // here i have to throw exception if password did not match

        }catch (Exception e){
            throw new RuntimeException("no such user found");
        }
    }
}
