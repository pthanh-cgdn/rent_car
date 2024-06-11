package services;

import models.Cars;
import models.DuplicateCarIdException;
import models.Order;
import repositories.CarRepository;
import repositories.OrderRepository;

import java.util.ArrayList;

public class CustomerService {
    private CarRepository carRepo = new CarRepository();
    private OrderRepository orderRepo = new OrderRepository();

    public String viewAvailable() {
        return carRepo.viewAvailable();
    }

    public ArrayList<Cars> searchByCarName(String[] carName) {
        return carRepo.searchByCarName(carName);
    }

    public String view(ArrayList<Cars> cars) {
        return carRepo.view(cars);
    }

    public boolean addOrder(Order order, String customerId) {
        order.setCustomerId(customerId);
        return orderRepo.addOrder(order);
    }

    public String viewOrder(String customerId) {
        return orderRepo.viewOrderByCustomerId(customerId);
    }

    public ArrayList<Cars> searchByDate(String[] rentDate) {
        return carRepo.searchByDate(rentDate);
    }
}
