package com.mike.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MikeController {

    @GetMapping("/")
    public String greet(){
        return "Hello!";
    }
}
