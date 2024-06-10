package repositories;

import models.Customer;

import java.io.*;
import java.util.*;

public class LoginRepository {
    private final String PATH_LOGIN = "src/repositories/data/loginData.dat";
    private CustomerRepository customerRepository = new CustomerRepository();

    public void add(String[] register) {
        LinkedHashMap<String, String> loginUserList = new LinkedHashMap<>();
        loginUserList = getAll();
        loginUserList.put(register[0], register[1]);
        writeToFile(loginUserList, PATH_LOGIN);
    }


    public boolean validateLoginCustomer(String[] loginData) {
        LinkedHashMap<String, String> login = new LinkedHashMap<>();
        login = getAll();
        return login.containsKey(loginData[0]) && login.get(loginData[0]).equals(loginData[1]);
    }

    private LinkedHashMap<String, String> getAll() {
        LinkedHashMap<String, String> loginData = new LinkedHashMap<>();
        try {
            FileInputStream fis = new FileInputStream(PATH_LOGIN);
            ObjectInputStream ois = new ObjectInputStream(fis);
            loginData = (LinkedHashMap<String, String>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginData;
    }

    public void writeToFile(LinkedHashMap<String, String> loginList, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(loginList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
