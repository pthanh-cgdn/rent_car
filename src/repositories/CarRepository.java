package repositories;

import models.Cars;
import models.DuplicateCarIdException;

import java.io.*;
import java.util.ArrayList;
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

}
