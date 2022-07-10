package com.restaurant_manager.restaurant_manager.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant_manager.restaurant_manager.models.users.UserService.UserService;
import com.restaurant_manager.restaurant_manager.models.users.dto.UserRegistryDTO;


@Controller
@RequestMapping("/api/register")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistryDTO newUserRegistryDTO() {
        return new UserRegistryDTO();
    }

    @GetMapping
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("get register ok");
    }

    @PostMapping (consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> postMethodName(@RequestBody UserRegistryDTO user) {
        System.out.println(user.getName());
        userService.save(user);
        Map<String, String> map = new java.util.HashMap<>();
        map.put("Rest", "Usuario registrado correctamente");
        return ResponseEntity.ok(map);
    }

}
