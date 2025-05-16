package com.example.Employee.Management.System.service;


import com.example.Employee.Management.System.model.Users;
import com.example.Employee.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public Users createUsers(Users newUser) {
        return userRepository.save(newUser);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUsersById(int id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
    }


    public Users updateUsers(int id , Users updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUsersById(int id) {
        userRepository.deleteById(id);
    }

}
