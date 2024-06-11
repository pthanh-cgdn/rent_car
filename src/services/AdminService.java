package services;

import models.*;
import repositories.CarRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class AdminService {
    private CarRepository carRepo = new CarRepository();
    private OrderRepository orderRepo = new OrderRepository();
    private CustomerRepository customerRepo = new CustomerRepository();
    public boolean addCar(Cars car) {
        try {
            carRepo.addCar(car);
        } catch (DuplicateCarIdException e){
            System.out.println("This carId already exists");
            return false;
        }
        return true;
    }

    public Cars contain(String carId) {
        return carRepo.contain(carId);
    }

    public void delete(Cars car) {
        carRepo.delete(car);
    }

    public String viewAll() {
        return carRepo.viewAll();
    }

    public String viewAvailable() {
        return carRepo.viewAvailable();
    }

    public String viewOrder() {
        return orderRepo.viewAll();
    }

    public String viewCustomer() {
        return customerRepo.viewAll();
    }

    public void verifyCustomer(ArrayList<String> accountNames, Admin admin) {
        customerRepo.verifyCustomer(accountNames,admin);
    }

    public boolean updateOrderStatus(String[] orderStatus) {
        return orderRepo.updateOrderStatus(orderStatus);
    }
}
