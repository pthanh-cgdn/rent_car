package models;

import javax.swing.*;
import java.io.Serializable;

public class Cars implements Serializable {
    private String id;
    private String carId;
    private String brand;
    private String name;
    private String model;
    private int manufactureYear;
    private String color;
    private int seats;
    private boolean isAvailable = true;
    private int trips;
    private float rating;
    private int rentPrice;

    public Cars(String carId,String brand, String name, String model, int manufactureYear, String color, int seats, int rentPrice) {
        this.carId = carId;
        this.brand = brand;
        this.name = name;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.color = color;
        this.seats = seats;
        this.rentPrice = rentPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public float getRating() {
        return rating;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int id = (int) Math.floor(Math.random()*9999);
        this.id = "car-"+id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    public String toString() {
       return "CarId: "+getId()+", Car plate number: "+getCarId()+", Car brand: "+getBrand()+", Car name: "+getName()+", Car model: "+getModel()+", Car manufacture year: "+getManufactureYear()+", Car color: "+getColor()+", Car seat: "+getSeats()+", Car rent price: "+getRentPrice()+", Car availability: "+isAvailable()+", Car's completed trip: "+getTrips()+", Car's Rating: "+getRating();
    }

}
