package com.restaurant_manager.restaurant_manager.models.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant_manager.restaurant_manager.models.users.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    
    public User findById(long id);
    public User findByEmail(String email);

}
