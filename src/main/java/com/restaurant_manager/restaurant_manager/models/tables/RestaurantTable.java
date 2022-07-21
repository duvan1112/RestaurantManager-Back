package com.restaurant_manager.restaurant_manager.models.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "capacity", nullable = false, unique = false, length = 2)
    private int capacity;

    @Column(name = "isAvailable", nullable = false, unique = false)
    private boolean isAvailable;

    public RestaurantTable() {
        this.isAvailable = true;
    }

    public RestaurantTable(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param l the id to set
     */
    public void setId(long l) {
        this.id = l;
    }

    /**
     * @return int return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return boolean return the isAvailable
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * @param isAvailable the isAvailable to set
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
