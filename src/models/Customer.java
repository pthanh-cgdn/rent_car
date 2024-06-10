package models;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String idCard;
    private String carDriverLicence;
    private String accountName;
    private boolean isVerified;
    private Admin admin;

    public Customer(String name, String address, String phone, String email, String idCard, String carDriverLicence) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
        this.carDriverLicence = carDriverLicence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCarDriverLicence() {
        return carDriverLicence;
    }

    public void setCarDriverLicence(String carDriverLicence) {
        this.carDriverLicence = carDriverLicence;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int id = (int) Math.floor(Math.random()*9999);
        this.id = "cus-"+id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified() {
        this.isVerified = this.admin != null;
    }

    public void setAdmin(Admin admin) {
        if(admin != null) {
            this.admin = admin;
        }
    }

    public String toString(){
        return  "CustomerID: "+id + ", AccountName:"+accountName+", Name:" + name + ", Address: " + address + ", Phone: " + phone + ", Email: " + email + ", ID No: " + idCard + ", Driver Licence: " + carDriverLicence+ ", isVerified: " + isVerified;
    }
}
