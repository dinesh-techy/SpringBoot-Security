package com.springboot.security.controller;

import com.springboot.security.models.Users;
import com.springboot.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    // Here strength denotes the number of rounds
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @PostMapping("createUser")
    public ResponseEntity<String> createUser(@RequestBody Users userReq){
        userReq.setPassword(encoder.encode(userReq.getPassword()));
        userRepo.save(userReq);
        return ResponseEntity.accepted().body("User Saved");
    }
}
