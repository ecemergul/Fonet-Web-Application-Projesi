package com.example.projectEmlak.service;

import com.example.projectEmlak.api.model.EstateCompRegistrationBody;
import com.example.projectEmlak.api.model.LoginBody;
import com.example.projectEmlak.exception.UserAlreadyExistsException;
import com.example.projectEmlak.model.EstateCompany;
import com.example.projectEmlak.model.dao.EstateCompanyDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstateCompanyService {

    private EstateCompanyDAO estateCompanyDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    @Autowired
    public EstateCompanyService(EstateCompanyDAO estateCompanyDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.estateCompanyDAO = estateCompanyDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public EstateCompany registerEstateCompany(EstateCompRegistrationBody estateCompRegistrationBody) throws UserAlreadyExistsException {
        if (estateCompanyDAO.findByAgentEmailIgnoreCase(estateCompRegistrationBody.getEmail()).isPresent()
                || estateCompanyDAO.findByAgentUsernameIgnoreCase(estateCompRegistrationBody.getAgentUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        EstateCompany company = new EstateCompany();
        company.setAgentEmail(estateCompRegistrationBody.getEmail());
        company.setEstateAgentFirstName(estateCompRegistrationBody.getEstateAgentFirstName());
        company.setEstateAgentLastName(estateCompRegistrationBody.getEstateAgentLastName());
        company.setAgentUsername(estateCompRegistrationBody.getAgentUsername());
        company.setPhone(estateCompRegistrationBody.getPhone());
        company.setCompanyName(estateCompRegistrationBody.getCompanyName());
        company.setFax(estateCompRegistrationBody.getFax());
        company.setCompAddress(estateCompRegistrationBody.getCompAddress());
        company.setAgentPassword(encryptionService.encryptPassword(estateCompRegistrationBody.getAgentPassword()));
        company = estateCompanyDAO.save(company);
        return company;
    }

    public String loginCompany(LoginBody loginBody) {
        Optional<EstateCompany> optionalEstateCompany = estateCompanyDAO.findByAgentUsernameIgnoreCase(loginBody.getUsername());
        if (optionalEstateCompany.isPresent()) {
            EstateCompany company = optionalEstateCompany.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), company.getAgentPassword())) {
                return jwtService.generateJWT(company);
            }
        }
        return null;
    }

}
