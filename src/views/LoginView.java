package views;

import models.Customer;

import java.util.Scanner;

public class LoginView {
    private AdminView adminView;
    private CustomerView customerView;
    public int startView(){
        Scanner input = new Scanner(System.in);
        String choice;
        System.out.println("Please choose your role");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("0. Exit");
        do {
            System.out.println("Please input your choice");
            choice = input.nextLine();
        } while (!choice.matches("[0-2]"));
        return Integer.parseInt(choice);
    }

    public String[] login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your account name:");
        String name = scanner.nextLine();
        System.out.println("Please input your password");
        String password = scanner.nextLine();
        return new String[]{name, password};
    }


    public boolean currentUserConfirm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you already have account? (Y for Yes, N for No)");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    public String[] registerView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your account name");
        String account = scanner.nextLine();
        System.out.println("Please input your password");
        String password = scanner.nextLine();
        return new String[] {account,password};
    }

    public Customer fillInforView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your Name:");
        String name = scanner.nextLine();
        System.out.println("Please input your address:");
        String address = scanner.nextLine();
        System.out.println("Please input your phone number:");
        String phone = scanner.nextLine();
        System.out.println("Please input your email:");
        String email = scanner.nextLine();
        System.out.println("Please input your ID number:");
        String idCard = scanner.nextLine();
        System.out.println("Please input your driver licence number:");
        String driverLicence = scanner.nextLine();
        return new Customer(name, address, phone, email, idCard, driverLicence);
    }

    public void waitToVerifyAccount() {
        System.out.println("Please wait for your account to be verified");
    }

    public void inactivateView() {
        System.out.println("You are unable to Login");
    }
}
