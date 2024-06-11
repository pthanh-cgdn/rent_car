package services;

import models.Customer;
import repositories.CustomerRepository;
import repositories.LoginRepository;

public class LoginService {
    LoginRepository loginRepository = new LoginRepository();
    CustomerRepository customerRepository = new CustomerRepository();

    public boolean validateLoginCustomer(String[] loginData){
        return loginRepository.validateLoginCustomer(loginData);
    }

    public boolean add(String[] register) {
        loginRepository.add(register);
        return true;
    }

    public boolean addCustomer(Customer customer){
        customerRepository.addCustomer(customer);
        return true;
    }

    public boolean checkVerify(String loginDatum) {
        return customerRepository.checkVerify(loginDatum);
    }

    public Customer getCustomerByAccountName(String loginDatum) {
        return customerRepository.getCustomerByAccountName(loginDatum);
    }
}
