package com.restaurant_manager.restaurant_manager.models.products;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = false,length = 50)
    private String name;

    @Column(name = "price", nullable = false, unique = false)
    private double price;

    @Column(name = "description", nullable = true, unique = false,length = 200)
    private String description;

    @Column(name = "preparationTime", nullable = true, unique = false)
    private long preparationTime;

    @Column(name = "type", nullable = true, unique = false ,length = 50)
    private String type;

    Product() {
    }

    public Product(int id, String name, double price, String description, long preparationTime, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.preparationTime = preparationTime;
        this.type = type;
    }

    public Product(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * @return int return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return double return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Time return the preparationTime
     */
    public long getPreparationTime() {
        return preparationTime;
    }

    /**
     * @param preparationTime the preparationTime to set
     */
    public void setPreparationTime(long preparationTime) {
        this.preparationTime = preparationTime;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
