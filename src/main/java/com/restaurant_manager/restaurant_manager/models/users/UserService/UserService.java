package com.restaurant_manager.restaurant_manager.models.users.UserService;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.restaurant_manager.restaurant_manager.models.users.User;
import com.restaurant_manager.restaurant_manager.models.users.dto.UserRegistryDTO;

public interface UserService extends UserDetailsService{
    public User save(UserRegistryDTO userRegistryDTO);
}
