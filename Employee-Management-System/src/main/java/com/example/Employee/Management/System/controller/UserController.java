package com.example.Employee.Management.System.controller;


import com.example.Employee.Management.System.model.Users;
import com.example.Employee.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Success endpoint
    @GetMapping("/success")
    public void loginSuccess() {
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Users getUsersById(@PathVariable int id) {
        return userService.getUsersById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Users addNewUsers(@RequestBody Users user) {
        return userService.createUsers(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Users updateUsers(@PathVariable int id, @RequestBody Users updatedUser){
        return userService.updateUsers(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUsersById(int id) {
        userService.deleteUsersById(id);
    }

}
