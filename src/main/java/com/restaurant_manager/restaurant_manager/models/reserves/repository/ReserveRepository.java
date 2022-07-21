package com.restaurant_manager.restaurant_manager.models.reserves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant_manager.restaurant_manager.models.reserves.Reserve;
import com.restaurant_manager.restaurant_manager.models.users.User;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    Reserve findById(long id);
    List<Reserve> findAllByClient(User client);

}
