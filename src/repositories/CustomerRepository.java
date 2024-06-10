package repositories;

import models.Admin;
import models.Cars;
import models.Customer;

import java.io.*;
import java.util.ArrayList;

public class CustomerRepository {
    private final String PATH_CUSTOMER = "src/repositories/data/customers.dat";

    public void addCustomer(Customer customer) {
        ArrayList<Customer> customers = getAll();
        customer.setId();
        for (Customer c : customers) {
            if (c.getId().equals(customer.getId())) {
                customer.setId();
            }
        }
        customers.add(customer);
        writeToFile(customers, PATH_CUSTOMER);
    }

    public void writeToFile(ArrayList<Customer> customers, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(customers);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(PATH_CUSTOMER);
            ObjectInputStream ois = new ObjectInputStream(fis);
            customers = (ArrayList<Customer>) ois.readObject();
            if (fis != null) {
                fis.close();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerByAccountName(String accountName) {
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList= getAll();
        for (Customer customer : customerList) {
            if (customer.getAccountName().equals(accountName)) {
                return customer;
            }
        }
        return null;
    }


    public String viewAll() {
        String customerList="";
        ArrayList<Customer> customers = getAll();
        for (Customer customer : customers) {
            customerList += customer.toString()+"\n";
        }
        return customerList;
    }

    public void verifyCustomer(ArrayList<String> accountNames, Admin admin) {
        ArrayList<Customer> customers = new ArrayList<>();
        customers= getAll();
        for (Customer customer : customers) {
            if(accountNames.contains(customer.getAccountName())) {
                customer.setAdmin(admin);
                customer.setVerified();
            }
        }
        writeToFile(customers, PATH_CUSTOMER);
    }
}
