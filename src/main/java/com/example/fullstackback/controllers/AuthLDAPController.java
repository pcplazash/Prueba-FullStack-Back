package com.example.fullstackback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthLDAPController {
    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }
}
