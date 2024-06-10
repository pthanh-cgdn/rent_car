package models;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Admin implements Serializable {
    private String accountName;
    private String email;
    private String role;
    private static LinkedHashMap<String, String> admins = new LinkedHashMap<>();
    static{
        admins.put("admin", "Admin123456!");
        admins.put("Manager", "Manager123456!");
    }

    private Admin(String name, String email) {
        this.accountName = name;
        this.email = email;
    }

    public String getName() {
        return accountName;
    }

    public void setName(String name) {
        this.accountName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Admin checkAccount (String accountName, String password){
        if(admins.containsKey(accountName)&&admins.get(accountName).equals(password)) {
            return new Admin(accountName, password);
        }
        return null;
    }
    public void verifyCustomer(Customer customer) {
        customer.setAdmin(this);
    }
}
