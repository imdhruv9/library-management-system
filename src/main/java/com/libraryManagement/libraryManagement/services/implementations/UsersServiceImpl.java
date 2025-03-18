package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.Users;
import com.libraryManagement.libraryManagement.repository.UsersRepository;
import com.libraryManagement.libraryManagement.services.UsersService;
import com.libraryManagement.libraryManagement.utill.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
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
            String encodedPassword = passwordService.encodePassword(users.getPassword());
            users.setPassword(encodedPassword);
        return usersRepository.save(users);

        }catch (Exception e){
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

}
