package services;

import models.Cars;
import models.DuplicateCarIdException;
import models.Order;
import repositories.CarRepository;
import repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Date;

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

    public int getRentAmount(Order order) {
        ArrayList<Cars> cars = carRepo.getAll();
        int rentPrice = 0;
        for(Cars car : cars) {
            if(order.getCarId().equals(car.getId())) {
                rentPrice = car.getRentPrice();
            }
        }
        Date startDate = new Date(Integer.parseInt(order.getStartDate().substring(6)),Integer.parseInt(order.getStartDate().substring(3,5)),Integer.parseInt(order.getStartDate().substring(0,2)));
        Date endDate = new Date(Integer.parseInt(order.getEndDate().substring(6)),Integer.parseInt(order.getEndDate().substring(3,5)),Integer.parseInt(order.getEndDate().substring(0,2)));
        int rentDay = (int) ((endDate.getTime() - startDate.getTime())/(24*60*60*1000));
        return rentDay*rentPrice;
    }

    public void rateCar(String[] orderId, String customerId) {
        orderRepo.rateCar(orderId, customerId);
    }

}
