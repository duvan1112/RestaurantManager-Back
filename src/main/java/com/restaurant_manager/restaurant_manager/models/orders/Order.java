package com.restaurant_manager.restaurant_manager.models.orders;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.restaurant_manager.restaurant_manager.models.products.Product;
import com.restaurant_manager.restaurant_manager.models.reserves.Reserve;
import com.restaurant_manager.restaurant_manager.models.tables.RestaurantTable;
import com.restaurant_manager.restaurant_manager.models.users.User;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "state", nullable = false, unique = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private User worker;

    @ManyToOne
    @JoinColumn(name = "reserve_id", referencedColumnName = "id")
    private Reserve reserve;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private RestaurantTable table;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "bills", joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;

    @Column(name = "total_price")
    private double totalPrice;

    public Order() {
        date = LocalDateTime.now();
    }

    public Order(String state, User client, User worker, Reserve reserve, List<Product> products, double totalPrice) {
        this.state = state;
        this.client = client;
        this.worker = worker;
        this.reserve = reserve;
        this.date = LocalDateTime.now();
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Order(String state, User client, User worker, Reserve reserve, RestaurantTable table, List<Product> products,
            double totalPrice) {
        this.state = state;
        this.client = client;
        this.worker = worker;
        this.reserve = reserve;
        this.table = table;
        this.date = LocalDateTime.now();
        this.products = products;
        this.totalPrice = totalPrice;
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return User return the client
     */
    public User getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(User client) {
        this.client = client;
    }

    /**
     * @return User return the worker
     */
    public User getWorker() {
        return worker;
    }

    /**
     * @param worker the worker to set
     */
    public void setWorker(User worker) {
        this.worker = worker;
    }

    /**
     * @return Reserve return the reserve
     */
    public Reserve getReserve() {
        return reserve;
    }

    /**
     * @param reserve the reserve to set
     */
    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    /**
     * @return RestaurantTable return the table
     */
    public RestaurantTable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    /**
     * @return LocalDateTime return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return List<Product> return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return double return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}