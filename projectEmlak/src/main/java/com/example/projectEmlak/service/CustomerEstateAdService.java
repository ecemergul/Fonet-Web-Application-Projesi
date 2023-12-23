package com.example.projectEmlak.service;

import com.example.projectEmlak.api.model.CustomerEstateAdRegistrationBody;
import com.example.projectEmlak.exception.AdAlreadyExistsException;
import com.example.projectEmlak.model.Customer;
import com.example.projectEmlak.model.CustomerEstateAd;
import com.example.projectEmlak.model.dao.CustomerDAO;
import com.example.projectEmlak.model.dao.CustomerEstateAdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerEstateAdService {

    private CustomerEstateAdDAO customerEstateAdDAO;

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerEstateAdService(CustomerEstateAdDAO customerEstateAdDAO, CustomerDAO customerDAO) {
        this.customerEstateAdDAO = customerEstateAdDAO;
        this.customerDAO = customerDAO;
    }

    /*public List<EstateAd> getEstateAds() {
        return estateAdDAO.findAll();
    }*/

    public List<CustomerEstateAd> listAll(String keyword) {
        if (keyword != null) {
            return customerEstateAdDAO.searchByKeyword(keyword);
        } else {
            return (List<CustomerEstateAd>) customerEstateAdDAO.findAll();
        }
    }

    public Customer getObjectByUsername(String username) {
        return customerDAO.findByUsername(username);
    }

    public CustomerEstateAd addCustomerEstateAd(CustomerEstateAdRegistrationBody customerEstateAdRegistrationBody) throws AdAlreadyExistsException {
        Customer customer = null;
        if (customerDAO.findByUsernameIgnoreCase(customerEstateAdRegistrationBody.getCustomerUsername()).isPresent()) {
            customer = getObjectByUsername(customerEstateAdRegistrationBody.getCustomerUsername());
        } //yoksa öle bi customer username ne yap?

        if (customerEstateAdDAO.findByEstateTypeIgnoreCase(customerEstateAdRegistrationBody.getEstateType()).isPresent()
            && customerEstateAdDAO.findBySqrMeter(customerEstateAdRegistrationBody.getSqrMeter()).isPresent()
            && customerEstateAdDAO.findByRoomNum(customerEstateAdRegistrationBody.getRoomNum()).isPresent()
            && customerEstateAdDAO.findByFloorNum(customerEstateAdRegistrationBody.getFloorNum()).isPresent()
            && customerEstateAdDAO.findByAptFloor(customerEstateAdRegistrationBody.getAptFloor()).isPresent()
            && customerEstateAdDAO.findByHeatingTypeIgnoreCase(customerEstateAdRegistrationBody.getHeatingType()).isPresent()
            && customerEstateAdDAO.findByAdAddressIgnoreCase(customerEstateAdRegistrationBody.getAdAddress()).isPresent()) {

            throw new AdAlreadyExistsException();
        }

        CustomerEstateAd customerEstateAd = new CustomerEstateAd();
        customerEstateAd.setCustomer(customer);
        customer.addToCustomerAdList(customerEstateAd);
        customerEstateAd.setEstateType(customerEstateAdRegistrationBody.getEstateType());
        customerEstateAd.setSqrMeter(customerEstateAdRegistrationBody.getSqrMeter());
        customerEstateAd.setRoomNum(customerEstateAdRegistrationBody.getRoomNum());
        customerEstateAd.setFloorNum(customerEstateAdRegistrationBody.getFloorNum());
        customerEstateAd.setAptFloor(customerEstateAdRegistrationBody.getAptFloor());
        customerEstateAd.setHeatingType(customerEstateAdRegistrationBody.getHeatingType());
        customerEstateAd.setAdAddress(customerEstateAdRegistrationBody.getAdAddress());
        customerEstateAd = customerEstateAdDAO.save(customerEstateAd);
        return customerEstateAd;
    }

}
