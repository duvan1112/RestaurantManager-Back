package com.restaurant_manager.restaurant_manager.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restaurant_manager.restaurant_manager.models.orders.Order;
import com.restaurant_manager.restaurant_manager.models.orders.orderDTO.OrderDTO;
import com.restaurant_manager.restaurant_manager.models.orders.repository.OrderRepository;
import com.restaurant_manager.restaurant_manager.models.products.Product;
import com.restaurant_manager.restaurant_manager.models.products.repository.ProductRepository;
import com.restaurant_manager.restaurant_manager.models.reserves.Reserve;
import com.restaurant_manager.restaurant_manager.models.reserves.dto.ReserveDto;
import com.restaurant_manager.restaurant_manager.models.reserves.repository.ReserveRepository;
import com.restaurant_manager.restaurant_manager.models.tables.RestaurantTable;
import com.restaurant_manager.restaurant_manager.models.tables.repository.TableRepository;
import com.restaurant_manager.restaurant_manager.models.users.User;
import com.restaurant_manager.restaurant_manager.models.users.repository.UserRepository;

@Controller
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerRoleAdmin {

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

    // users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User userToUpdate = userRepository.findById(id).get();
        if (user.getName() != null || user.getName() != "")
            userToUpdate.setName(user.getName());
        if (user.getLastName() != null || user.getLastName() != "")
            userToUpdate.setLastName(user.getLastName());
        if (user.getEmail() != null || user.getEmail() != "")
            userToUpdate.setEmail(user.getEmail());
        if (user.getPassword() != null || user.getPassword() != "")
            userToUpdate.setPassword(user.getPassword());
        if (user.getRoles() != null || !user.getRoles().isEmpty())
            userToUpdate.setRoles(user.getRoles());
        return ResponseEntity.ok(userRepository.save(userToUpdate));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    // tables

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/tables")
    public ResponseEntity<?> getTables() {
        return ResponseEntity.ok(tableRepository.findAll());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/tables/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTable(@PathVariable Long id) {
        return ResponseEntity.ok(tableRepository.findById(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/tables/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTable(@RequestBody RestaurantTable table) {
        tableRepository.save(table);
        return ResponseEntity.ok(table);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/tables/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTable(@RequestBody RestaurantTable table, @PathVariable Long id) {
        tableRepository.findById(id).map(t -> {
            if (table.getId() != 0)
                t.setId((table.getId()));
            if (table.getCapacity() != 0)
                t.setCapacity(table.getCapacity());
            if (table.isAvailable() != t.isAvailable())
                t.setIsAvailable(table.isAvailable());
            return tableRepository.save(t);
        }).orElseGet(() -> {
            return tableRepository.save(table);
        });
        return ResponseEntity.ok("Table updated");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/tables/delete/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable Long id) {
        tableRepository.delete(
                tableRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid table Id:" + id)));
        return ResponseEntity.ok("Table deleted");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/tables/count", method = RequestMethod.GET)
    public ResponseEntity<?> getTableCount() {
        Map<String, Long> map = new HashMap<>();
        map.put("Ocupadas", tableRepository.countByIsAvailable(false));
        map.put("Disponibles", tableRepository.countByIsAvailable(true));
        return ResponseEntity.ok(map);
    }

    // products

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/products/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/products/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/products/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        productRepository.findById(id).map(p -> {
            if (product.getName() != null || product.getName() != "")
                p.setName(product.getName());
            if ((product.getPrice() != 0.0 || product.getPrice() != 0) && product.getPrice() > 0)
                p.setPrice(product.getPrice());
            if (product.getDescription() != null || product.getDescription() != "")
                p.setDescription(product.getDescription());
            if (product.getPreparationTime() != 0 || product.getPreparationTime() != 0.0)
                p.setPreparationTime(product.getPreparationTime());
            if (product.getType() != null || product.getType() != "")
                p.setType(product.getType());
            return productRepository.save(p);
        }).orElseGet(() -> {
            return productRepository.save(product);
        });
        return ResponseEntity.ok("Product updated");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "/products/delete/{name}")
    public ResponseEntity<?> deleteProduct(@PathVariable String nameString) {
        productRepository.delete(productRepository.findByName(nameString));
        return ResponseEntity.ok("Product deleted");
    }

    // orders

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/orders/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderRepository.findById(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/orders/add", method = RequestMethod.POST)
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDto) {
        Order order = orderDtoToOrder(orderDto);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/orders/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrder(@RequestBody OrderDTO orderDto, @PathVariable Long id) {
        Order order = orderDtoToOrder(orderDto);
        orderRepository.findById(id).map(o -> {
            if (order.getState() != null || order.getState() != "")
                o.setState(order.getState());
            if (order.getReserve() != null)
                o.setReserve(order.getReserve());
            if (order.getWorker() != null)
                o.setWorker(order.getWorker());
            if (order.getClient() != null)
                o.setClient(order.getClient());
            return orderRepository.save(o);
        }).orElseGet(() -> {
            return orderRepository.save(order);
        });
        return ResponseEntity.ok("Order updated");
    }

    private Order orderDtoToOrder(OrderDTO order) {
        Order o = new Order();
        o.setState(order.getState());
        if (order.getReserve() != 0)
            o.setReserve(reserveRepository.findById(order.getReserve()));
        if (order.getWorker() != 0)
            o.setWorker(userRepository.findById(order.getWorker()));
        o.setClient(userRepository.findById(order.getClient()));
        RestaurantTable table = tableRepository.findById(order.getTable());
        if (table != null) {
            if (table.isAvailable()) {
                o.setTable(table);
                table.setIsAvailable(false);
                tableRepository.save(table);
            }
        }
        List<Product> products = new ArrayList<>();
        order.getProducts().forEach(p -> {
            Product product = productRepository.findById(p)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + p));
            products.add(product);
            o.setTotalPrice(o.getTotalPrice() + product.getPrice());
        });
        o.setProducts(products);
        return o;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "/orders/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderRepository.delete(orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id)));
        return ResponseEntity.ok("Order deleted");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/orders/count", method = RequestMethod.GET)
    public ResponseEntity<?> getOrdersCount() {
        Map<String, Long> map = new HashMap<>();
        long active = orderRepository.countByState("Activa");
        map.put("Activas", active);
        map.put("Despachadas", orderRepository.count() - active);
        return ResponseEntity.ok(map);
    }

    // reserves

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/reserves/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllReserves() {
        return ResponseEntity.ok(reserveRepository.findAll());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/reserves/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReserve(@PathVariable Long id) {
        return ResponseEntity.ok(reserveRepository.findById(id));
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
