package com.restaurant_manager.restaurant_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/user")
public class ControllerRoleUser {
    
    @GetMapping (value = "/test")
    public String mytest() {
        return "test";
    }
}
