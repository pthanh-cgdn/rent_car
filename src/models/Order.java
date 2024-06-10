package models;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String id;
    private String customerId;
    private String carId;
    private String startDate;
    private String endDate;
    private int rentAmount;

    public Order(String carId, String startDate, String endDate) {
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public int getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(int rentAmount) {
        this.rentAmount = rentAmount;
    }

    public void setId() {
        int id = (int) Math.floor(Math.random()*9999);
        this.id = "order-"+id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return getId() + "," + customerId + "," + carId + "," + startDate + "," + endDate;
    }

}
