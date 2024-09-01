package com.springboot.security.service;

import com.springboot.security.models.Users;
import com.springboot.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // Gets userData from repository
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByusername(username);
        if(user==null){
            System.out.println("User Not found");
            throw new UsernameNotFoundException("User Not found");
        }
        return new UserPrincipal(user);
    }
}
