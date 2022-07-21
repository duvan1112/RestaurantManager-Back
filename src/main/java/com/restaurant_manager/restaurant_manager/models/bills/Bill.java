package com.restaurant_manager.restaurant_manager.models.bills;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bill_id")
    private long billId;

    @Column(name = "product_id")
    private long productId;

    public Bill(long billId, long productId) {
        this.billId = billId;
        this.productId = productId;
    }

    public Bill() {
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
     * @return long return the billId
     */
    public long getBillId() {
        return billId;
    }

    /**
     * @param billId the billId to set
     */
    public void setBillId(long billId) {
        this.billId = billId;
    }

    /**
     * @return long return the productId
     */
    public long getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

}