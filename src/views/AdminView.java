package views;

import models.Admin;
import models.Cars;

import java.util.Scanner;

public class AdminView {
    private Admin admin;
    public AdminView(Admin admin){
        this.admin = admin;
    }

    public int menuView(){
        String choice="";
        Scanner input =  new Scanner(System.in);

        System.out.println("Please select an option:");
        System.out.println("1. Add new Car");
        System.out.println("2. Delete a Car");
        System.out.println("3. Edit a Car");
        System.out.println("4. View all Cars");
        System.out.println("5. View all orders");
        System.out.println("6. View all customer");
        System.out.println("7. Verify customer");
        System.out.println("8. Update order status");
        System.out.println("0. Back to main menu");
        do {
            System.out.println("Please input your choice");
            choice = input.nextLine();
        } while(!choice.matches("[0-8]"));
        return Integer.parseInt(choice);
    }

    public Cars addView() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the car's plate number:");
        String carId = input.nextLine();
        System.out.println("Please enter the brand of the car:");
        String brand = input.nextLine();
        System.out.println("Please enter the name of the car");
        String name = input.nextLine();
        System.out.println("Please enter the model of the car");
        String model = input.nextLine();
        System.out.println("Please enter the year of the car");
        int year = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the color of the car");
        String color = input.nextLine();
        System.out.println("Please enter number of seat of the car");
        int seat = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the car's rent price");
        int rentPrice = Integer.parseInt(input.nextLine());
        return new Cars(carId,brand,name, model,year,color,seat,rentPrice);
    }

    public void showMessage(boolean result) {
        if(result) {
            System.out.println("Your command is successfully executed");
        } else {
            System.out.println("Your command is fail to executed");
        }
    }

    public String findCarIdView() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the car's id:");
        String carId = input.nextLine();
        return carId;
    }

    public boolean confirmDelete(Cars car) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please confirm delete the car "+car.toString()+" (choose Y for Yes, N for No) :");
        boolean confirmDelete = input.nextLine().equals("Y");
        return confirmDelete;
    }

    public boolean confirmEdit(Cars car) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please confirm Edit the car "+car.toString()+" (choose Y for Yes, N for No) :");
        boolean confirmDelete = input.nextLine().equals("Y");
        return confirmDelete;
    }

    public void showMessageNotContain(String carId) {
        System.out.println("CarId "+carId+" is not contain in car list");
    }

    public Cars editView(Cars car) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the car's id:");
        System.out.print(car.getCarId()+": ");
        String carId = input.nextLine();
        System.out.println("Please enter the brand of the car:");
        System.out.print(car.getBrand()+": ");
        String brand = input.nextLine();
        System.out.println("Please enter the name of the car");
        System.out.print(car.getName()+": ");
        String name = input.nextLine();
        System.out.println("Please enter the model of the car");
        System.out.print(car.getModel()+": ");
        String model = input.nextLine();
        System.out.println("Please enter the year of the car");
        System.out.print(car.getManufactureYear()+": ");
        int year = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the color of the car");
        System.out.print(car.getColor()+": ");
        String color = input.nextLine();
        System.out.println("Please enter number of seat of the car");
        System.out.print(car.getSeats()+": ");
        int seat = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the car's rent price");
        System.out.print(car.getRentPrice()+": ");
        int rentPrice = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the car's availability (Y for Yes, N for No)");
        System.out.print((car.isAvailable())+" :");
        car.setAvailable(input.nextLine().equals("Y"));
        return new Cars(carId,brand,name, model,year,color,seat,rentPrice);
    }

    public void viewAll(String carList) {
        System.out.println(carList);
    }

    public String[] verifyCustomerView() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the customer account name: (seperate by ',')");
        String customerName = input.nextLine();
        return customerName.split(",");
    }

    public String[] viewOrderStatus() {
        Scanner input = new Scanner(System.in);
        String orderStatus;
        System.out.println("Please enter the order id:");
        String orderId = input.nextLine();
        System.out.println("Please update order status: ");
        System.out.println("0. Cancel");
        System.out.println("1. Start");
        System.out.println("2. Completed");
        do {
            System.out.println("Please input your choice");
            orderStatus = input.nextLine();
        } while(!orderStatus.matches("[0-2]"));
        return new String[] {orderId,orderStatus};
    }
}
