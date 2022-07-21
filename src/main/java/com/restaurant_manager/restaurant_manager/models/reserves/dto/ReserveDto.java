package com.restaurant_manager.restaurant_manager.models.reserves.dto;

import java.time.LocalDateTime;

public class ReserveDto {

    private LocalDateTime reserveDate;
    private boolean isDispatched;
    private LocalDateTime dispatchedDate;
    private int amountOfPeople;
    private long client;

    public ReserveDto() {
    }

    public ReserveDto(LocalDateTime reserveDate, boolean isDispatched, LocalDateTime dispatchedDate, int amountOfPeople,
            long client) {
        this.reserveDate = reserveDate;
        this.isDispatched = isDispatched;
        this.dispatchedDate = dispatchedDate;
        this.amountOfPeople = amountOfPeople;
        this.client = client;
    }

    /**
     * @return LocalDateTime return the reserveDate
     */
    public LocalDateTime getReserveDate() {
        return reserveDate;
    }

    /**
     * @param reserveDate the reserveDate to set
     */
    public void setReserveDate(LocalDateTime reserveDate) {
        this.reserveDate = reserveDate;
    }

    /**
     * @return boolean return the isDispatched
     */
    public boolean isIsDispatched() {
        return isDispatched;
    }

    /**
     * @param isDispatched the isDispatched to set
     */
    public void setIsDispatched(boolean isDispatched) {
        this.isDispatched = isDispatched;
    }

    /**
     * @return LocalDateTime return the dispatchedDate
     */
    public LocalDateTime getDispatchedDate() {
        return dispatchedDate;
    }

    /**
     * @param dispatchedDate the dispatchedDate to set
     */
    public void setDispatchedDate(LocalDateTime dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    /**
     * @return int return the amountOfPeople
     */
    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    /**
     * @param amountOfPeople the amountOfPeople to set
     */
    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    /**
     * @return long return the client
     */
    public long getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(long client) {
        this.client = client;
    }

}
