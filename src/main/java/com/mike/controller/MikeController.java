package com.mike.controller;

import com.mike.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MikeController {
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/greet")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse("Hello",
                List.of("Java", "Ruby", "Python"),
                new Person("Alex", 28, 30000));
        return response;
    }

}
record Person(String name, int age, double savings){}
record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person){}
