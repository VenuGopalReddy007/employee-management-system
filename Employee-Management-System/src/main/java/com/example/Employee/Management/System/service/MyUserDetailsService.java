package com.example.Employee.Management.System.service;


import com.example.Employee.Management.System.model.UserPrincipal;
import com.example.Employee.Management.System.model.Users;
import com.example.Employee.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("User Not Found! ");
            throw new UsernameNotFoundException("user not found exception");
        }
        return new UserPrincipal(user);
    }
}
