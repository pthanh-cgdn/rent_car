package repositories;

import models.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderRepository {
    final static String PATH_ORDER = "src/repositories/data/orders.csv";

    public boolean addOrder(Order order) {
        ArrayList<Order> orders = getAll();
        order.setId();
        for (Order o : orders) {
            if (order.getId().equals(o.getId())) {
                order.setId();
            }
        }
        if(checkOrder(order)) {
            orders.add(order);
            writeToFile(orders, PATH_ORDER, false);
            return true;
        }
        return false;
    }

    public boolean checkOrder(Order order) {
        ArrayList<Order> orders = getAll();
        Date startDay1 = new Date(Integer.parseInt(order.getStartDate().substring(0,2)),Integer.parseInt(order.getStartDate().substring(3,5)),Integer.parseInt(order.getStartDate().substring(6,10)));
        Date endDay1 = new Date(Integer.parseInt(order.getEndDate().substring(0,2)),Integer.parseInt(order.getEndDate().substring(3,5)),Integer.parseInt(order.getEndDate().substring(6,10)));
        Date startDay2;
        Date endDay2;
        for (Order o : orders) {
            if (order.getCarId().equals(o.getCarId())) {
                startDay2 = new Date(Integer.parseInt(o.getStartDate().substring(0,2)),Integer.parseInt(o.getStartDate().substring(3,5)),Integer.parseInt(o.getStartDate().substring(6,10)));
                endDay2 = new Date(Integer.parseInt(o.getEndDate().substring(0,2)),Integer.parseInt(o.getEndDate().substring(3,5)),Integer.parseInt(o.getEndDate().substring(6,10)));
                if((!startDay1.after(endDay2)&&!startDay1.before(startDay2))||(!endDay1.after(endDay2)&&!endDay1.before(startDay2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void writeToFile(ArrayList<Order> orders,String path, boolean append) {
        File file = new File(path);
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file, append);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Order order : orders) {
                bufferedWriter.write(order.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing file");
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing file");
            }
        }
    }

    public ArrayList<Order> getAll() {
        File file = new File(PATH_ORDER);
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Order order;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                order = new Order(data[2], data[3], data[4]);
                order.setId(data[0]);
                order.setCustomerId(data[1]);
                orderList.add(order);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }
        return orderList;
    }

    public String viewAll() {
        ArrayList<Order> orderList = getAll();
        String order = "";
        for (Order currentOrder : orderList) {
            order += currentOrder.toString() + "\n";
        }
        return order;
    }

    public String viewOrderByCustomerId(String customerId) {
        ArrayList<Order> orderList = getAll();
        String order = "";
        for (Order currentOrder : orderList) {
            if (currentOrder.getCustomerId().equals(customerId)) {
                order += currentOrder.toString() + "\n";
            }
        }
        return order;
    }
}
