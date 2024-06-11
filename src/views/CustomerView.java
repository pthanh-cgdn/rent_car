package views;

import models.Customer;
import models.Order;

import java.util.Scanner;

import static common.ExceptionHandler.*;

public class CustomerView {
    private Customer customer;

    public CustomerView(Customer customer) {
        this.customer = customer;
    }

    public int menuView() {
        String choice = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Please select an option:");
        System.out.println("1. View available Car");
        System.out.println("2. Search a Car by Car Brand & Car Name");
        System.out.println("3. Search available Cars by start and end date");
        System.out.println("4. Rent a Car");
        System.out.println("5. View your order");
        System.out.println("0. Back to main menu");
        do {
            System.out.println("Please input your choice");
            choice = input.nextLine();
        } while (!choice.matches("[0-5]"));
        return Integer.parseInt(choice);
    }

    public void view(String carList) {
        System.out.println(carList);
    }

    public String[] searchView() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input car's brand name:");
        String carBrand = input.nextLine();
        System.out.println("Please input car's name:");
        String carName = input.nextLine();
        return new String[]{carBrand, carName};
    }

    public Order rentView() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input car's id you want to rent:");
        String carId = checkCarExists();
        System.out.println("Please input start date:");
        String startDate = checkDateFormat();
        System.out.println("Please input end date:");
        String endDate = checkAndCompareDay(startDate);
        return new Order(carId, startDate, endDate);
    }

    public boolean confirmOrder(Order order) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please confirm your order: (Y for Yes, N for No)");
        return input.nextLine().equalsIgnoreCase("Y");
    }

    public void orderStatusView(boolean result) {
        if (result) {
            System.out.println("Your order status is success");
        } else {
            System.out.println("Your order status is fail. Please try again");
        }
    }

    public String[] searchByDate() {
        System.out.println("Please input start date:");
        String startDate = checkDateFormat();
        System.out.println("Please input end date:");
        String endDate = checkAndCompareDay(startDate);
        return new String[]{startDate,endDate};
    }
}
