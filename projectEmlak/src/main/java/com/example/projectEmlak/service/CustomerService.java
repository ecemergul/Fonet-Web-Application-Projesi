package com.example.projectEmlak.service;

import com.example.projectEmlak.api.model.CustomerRegistrationBody;
import com.example.projectEmlak.api.model.LoginBody;
import com.example.projectEmlak.exception.UserAlreadyExistsException;
import com.example.projectEmlak.model.Customer;
import com.example.projectEmlak.model.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {


    private CustomerDAO customerDAO;

    private EncryptionService encryptionService;

    private JWTService jwtService;

    @Autowired
    public CustomerService(CustomerDAO customerDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.customerDAO = customerDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public Customer registerCustomer(CustomerRegistrationBody customerRegistrationBody) throws UserAlreadyExistsException {
        if (customerDAO.findByEmailIgnoreCase(customerRegistrationBody.getEmail()).isPresent()
                || customerDAO.findByUsernameIgnoreCase(customerRegistrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        Customer customer = new Customer();
        customer.setEmail(customerRegistrationBody.getEmail());
        customer.setFirstName(customerRegistrationBody.getFirstName());
        customer.setLastName(customerRegistrationBody.getLastName());
        customer.setUsername(customerRegistrationBody.getUsername());
        customer.setCellPhoneNumber(customerRegistrationBody.getCellPhoneNumber());
        customer.setHomePhoneNumber(customerRegistrationBody.getHomePhoneNumber());
        customer.setPassword(encryptionService.encryptPassword(customerRegistrationBody.getPassword()));
        customer = customerDAO.save(customer);
        return customer;
    }

    public String loginCustomer(LoginBody loginBody) {
        Optional<Customer> optionalCustomer = customerDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), customer.getPassword())) {
                return jwtService.generateJWT(customer);
            }
        }
        return null;
    }

}
