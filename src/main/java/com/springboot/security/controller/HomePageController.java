package com.springboot.security.controller;

import com.springboot.security.models.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomePageController {

    private List<Student> studentArray = new ArrayList<>(List.of(new Student(1,"Dinesh",15),new Student(2,"Sathish",20)));
    @GetMapping("/")
    public String homePage(HttpServletRequest httpServletRequest){
        return "HomePage SessionId "+httpServletRequest.getSession().getId();
    }

    @GetMapping("getAllStudents")
    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.accepted().body(studentArray);
    }

    @GetMapping("getcsrf")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

    @PostMapping("addStudent")
    public ResponseEntity<List<Student>> getCsrfToken(@RequestBody Student student){
        studentArray.add(student);
        return ResponseEntity.accepted().body(studentArray);
    }
}
