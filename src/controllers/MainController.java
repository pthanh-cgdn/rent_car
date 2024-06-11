package controllers;

import models.*;
import repositories.CustomerRepository;
import services.AdminService;
import services.CustomerService;
import services.LoginService;
import views.AdminView;
import views.CustomerView;
import views.LoginView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainController {
    private static void adminController(Admin admin) {
        int choice;
        Cars car;
        AdminService adminService = new AdminService();
        boolean result;
        String carId;
        String carList;
        String orderList;
        boolean confirm;
        String customerList;
        ArrayList<String> accountNames = new ArrayList<>();

        if (admin != null) {
            AdminView adminView = new AdminView(admin);
            while (true) {
                choice = adminView.menuView();
                switch (choice) {
                    case 1:
                        car = adminView.addView();
                        result = adminService.addCar(car);
                        adminView.showMessage(result);
                        break;
                    case 2:
                        carId = adminView.findCarIdView();
                        car = adminService.contain(carId);
                        if (car != null) {
                            confirm = adminView.confirmDelete(car);
                            if (confirm) {
                                adminService.delete(car);
                                adminView.showMessage(true);
                            }
                        } else {
                            adminView.showMessageNotContain(carId);
                        }
                        break;
                    case 3:
                        carId = adminView.findCarIdView();
                        car = adminService.contain(carId);
                        if (car != null) {
                            confirm = adminView.confirmEdit(car);
                            if (confirm) {
                                car = adminView.editView(car);
                                adminService.delete(car);
                                adminService.addCar(car);
                                adminView.showMessage(true);
                            }
                        } else {
                            adminView.showMessageNotContain(carId);
                        }
                        break;
                    case 4:
                        carList = adminService.viewAll();
                        adminView.viewAll(carList);
                        break;
                    case 5:
                        orderList = adminService.viewOrder();
                        adminView.viewAll(orderList);
                        break;
                    case 6:
                        customerList = adminService.viewCustomer();
                        adminView.viewAll(customerList);
                        break;
                    case 7:
                        accountNames.addAll(Arrays.asList(adminView.verifyCustomerView())) ;
                        adminService.verifyCustomer(accountNames, admin);
                        break;
                    case 0:
                        return;
                }
            }
        }
    }
    private static void customerController(Customer customer) {
        int choice;
        ArrayList<Cars> cars;
        CustomerService customerService = new CustomerService();
        String carList;
        String orderList;
        boolean confirm;
        String[] carName;
        Order order;
        boolean result;

        if (customer != null) {
            CustomerView customerView = new CustomerView(customer);
            while (true) {
                choice = customerView.menuView();
                switch (choice) {
                    case 1:
                        carList = customerService.viewAvailable();
                        customerView.view(carList);
                        break;
                    case 2:
                        carName = customerView.searchView();
                        cars = customerService.searchByCarName(carName);
                        carList = customerService.view(cars);
                        customerView.view(carList);
                        break;
                    case 3:
                        order = customerView.rentView();
                        confirm = customerView.confirmOrder(order);
                        if (confirm) {
                            result = customerService.addOrder(order, customer.getId());
                            customerView.orderStatusView(result);
                        }
                        break;
                    case 4:
                        orderList = customerService.viewOrder(customer.getId());
                        customerView.view(orderList);
                        break;
                    case 0:
                        return;
                }
            }
        }
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        Customer customer = null;
        Admin admin = null;
        int choice;
        LoginService loginService = new LoginService();
        CustomerRepository customerRepo = new CustomerRepository();
        boolean result;
        String[] loginData;
        String[] register;

        boolean confirm;
        final int LOGIN_ATTEMPT = 3;
        int loginAttemp = 0;

        while (true) {
            choice = loginView.startView();
            switch (choice) {
                case 1:
                    while (loginAttemp < LOGIN_ATTEMPT) {
                        loginData = loginView.login();
                        if ((admin = Admin.checkAccount(loginData[0], loginData[1])) != null) {
                            break;
                        }
                        loginAttemp++;
                    }
                    if (loginAttemp >= LOGIN_ATTEMPT) {
                        loginView.inactivateView();
                    }
                    adminController(admin);
                    break;
                case 2:
                    confirm = loginView.currentUserConfirm();
                    if (!confirm) {
                        register = loginView.registerView();
                        result = loginService.add(register);
                        if (result) {
                            customer = loginView.fillInforView();
                            customer.setAccountName(register[0]);
                            result = loginService.addCustomer(customer);
                            if (result) {
                                loginView.waitToVerifyAccount();
                            }
                        }
                    } else {
                        while (loginAttemp < LOGIN_ATTEMPT) {
                            loginData = loginView.login();
                            if (loginService.validateLoginCustomer(loginData)) {
                                result = customerRepo.checkVerify(loginData[0]);
                                if(result) {
                                    customer = customerRepo.getCustomerByAccountName(loginData[0]);
                                } else {
                                    loginView.waitToVerifyAccount();
                                }
                                break;
                            }
                            loginAttemp++;
                        }
                        if (loginAttemp >= LOGIN_ATTEMPT) {
                            loginView.inactivateView();
                        } else {
                            customerController(customer);
                        }
                    }
                    break;
                    case 0:
                        return;
                }
            }
        }
    }
