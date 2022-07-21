package com.restaurant_manager.restaurant_manager.models.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant_manager.restaurant_manager.models.users.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String name);

}
    

