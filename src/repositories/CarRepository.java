package repositories;

import models.Cars;
import models.DuplicateCarIdException;
import models.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRepository {
    final String PATH_CAR = "src/repositories/data/cars.dat";

    public void addCar(Cars car) throws DuplicateCarIdException {
        ArrayList<Cars> cars = getAll();
        car.setId();
        for (Cars temp : cars){
            if (car.getId().equals(temp.getId())){
                car.setId();
            }
        }
        cars.add(car);
        writeToFile(cars, PATH_CAR);
    }

    public void writeToFile(ArrayList<Cars> cars,String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cars);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cars> getAll() {
        ArrayList<Cars> cars = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(PATH_CAR);
            ObjectInputStream ois = new ObjectInputStream(fis);
            cars = (ArrayList<Cars>) ois.readObject();
            if (fis != null) {
                fis.close();
                ois.close();
            }
        } catch (Exception e) {
        e.printStackTrace();
    }
        return cars;
    }

    public Cars contain(String carID) {
        ArrayList<Cars> cars = getAll();
        for (Cars car : cars) {
            if (car.getCarId().equals(carID)) {
                return car;
            }
        }
        return null;
    }

    public Cars containCarId(String id) {
        ArrayList<Cars> cars = getAll();
        for (Cars car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public void delete(Cars car) {
        ArrayList<Cars> cars = getAll();
        for (Cars temp : cars) {
            if (car.getCarId().equals(temp.getCarId())) {
                cars.remove(temp);
                break;
            }
        }
        writeToFile(cars, PATH_CAR);
    }

    public String viewAll() {
        ArrayList<Cars> cars = getAll();
        String carList = "";
        for (Cars car : cars) {
            carList += car.toString() + "\n";
        }
        return carList;
    }

    public String viewAvailable() {
        ArrayList<Cars> cars = getAll();
        String carList = "";
        for (Cars car : cars) {
            if (car.isAvailable()) {
                carList += car.toString() + "\n";
            }
        }
        return carList;
    }

    public ArrayList<Cars> searchByCarName(String[] carName) {
        List<Cars> cars = getAll();
        ArrayList<Cars> carList = new ArrayList<>();
        for (Cars car : cars) {
            if ((car.getBrand().equals(carName[0]) || carName[0].isEmpty()) && (car.getName().equals(carName[1]) || carName[1].isEmpty())) {
                carList.add(car);
            }
        }
        return carList;
    }

    public String view(ArrayList<Cars> cars) {
        String carList = "";
        for (Cars car : cars) {
            carList += car.toString() + "\n";
        }
        return carList;
    }

    public ArrayList<Cars> searchByDate(String[] rentDate) {
        ArrayList<Cars> cars = getAll();
        ArrayList<Cars> cars2 = new ArrayList<>();
        OrderRepository orderRepository = new OrderRepository();
        ArrayList<Order> orders = orderRepository.getAll();
        Date startDay = new Date(Integer.parseInt(rentDate[0].substring(0,2)),Integer.parseInt(rentDate[0].substring(3,5)),Integer.parseInt(rentDate[0].substring(6,10)));
        Date endDay = new Date(Integer.parseInt(rentDate[1].substring(0,2)),Integer.parseInt(rentDate[1].substring(3,5)),Integer.parseInt(rentDate[1].substring(6,10)));
        Date startDay2 = new Date();
        Date endDay2 = new Date();
        ArrayList<String> carList = new ArrayList<>();
        for (Order o:orders){
            startDay2 = new Date(Integer.parseInt(o.getStartDate().substring(0,2)),Integer.parseInt(o.getStartDate().substring(3,5)),Integer.parseInt(o.getStartDate().substring(6,10)));
            endDay2 = new Date(Integer.parseInt(o.getEndDate().substring(0,2)),Integer.parseInt(o.getEndDate().substring(3,5)),Integer.parseInt(o.getEndDate().substring(6,10)));
            if((!startDay.after(endDay2)&&!startDay.before(startDay2))||(!endDay.after(endDay2)&&!endDay.before(startDay2))){
                carList.add(o.getCarId());
            }
        }
        for (Cars car: cars){
            if (!carList.contains(car.getId())&&car.isAvailable()) {
                cars2.add(car);
            }
        }
        return cars2;
    }
}
