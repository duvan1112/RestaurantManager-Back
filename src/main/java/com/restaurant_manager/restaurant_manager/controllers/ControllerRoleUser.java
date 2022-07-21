package com.restaurant_manager.restaurant_manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant_manager.restaurant_manager.models.orders.repository.OrderRepository;
import com.restaurant_manager.restaurant_manager.models.products.repository.ProductRepository;
import com.restaurant_manager.restaurant_manager.models.reserves.Reserve;
import com.restaurant_manager.restaurant_manager.models.reserves.dto.ReserveDto;
import com.restaurant_manager.restaurant_manager.models.reserves.repository.ReserveRepository;
import com.restaurant_manager.restaurant_manager.models.tables.repository.TableRepository;
import com.restaurant_manager.restaurant_manager.models.users.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerRoleUser {
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ReserveRepository reserveRepository;

    // reserves

    @RequestMapping(value = "/reserves/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllReserves(@PathVariable Long id) {
        return ResponseEntity.ok(reserveRepository.findAllByClient(userRepository.findById(id).get()));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/reserves/add", method = RequestMethod.POST)
    public ResponseEntity<?> addReserve(@RequestBody ReserveDto reserveDto) {
        Reserve reserve = reserveDtoToReserve(reserveDto);
        reserveRepository.save(reserve);
        return ResponseEntity.ok(reserve);
    }

    private Reserve reserveDtoToReserve(ReserveDto reserveDto) {
        Reserve reserve = new Reserve();
        reserve.setReserveDate(reserveDto.getReserveDate());
        reserve.setIsDispatched(reserveDto.isIsDispatched());
        reserve.setDispatchedDate(reserveDto.getDispatchedDate());
        reserve.setAmountOfPeople(reserveDto.getAmountOfPeople());
        reserve.setClient(userRepository.findById(reserveDto.getClient()));
        return reserve;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "/reserves/delete/{id}")
    public ResponseEntity<?> deleteReserve(@PathVariable Long id) {
        reserveRepository.delete(reserveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reserve Id:" + id)));
        return ResponseEntity.ok("Reserve deleted");
    }
}
