package com.example.projectEmlak.service;

import com.example.projectEmlak.api.model.CompanyEstateAdRegistrationBody;
import com.example.projectEmlak.exception.AdAlreadyExistsException;
import com.example.projectEmlak.model.CompanyEstateAd;
import com.example.projectEmlak.model.CustomerEstateAd;
import com.example.projectEmlak.model.EstateCompany;
import com.example.projectEmlak.model.dao.CompanyEstateAdDAO;
import com.example.projectEmlak.model.dao.CustomerEstateAdDAO;
import com.example.projectEmlak.model.dao.EstateCompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyEstateAdService {

    private CompanyEstateAdDAO companyEstateAdDAO;
    private EstateCompanyDAO estateCompanyDAO;

    @Autowired
    public CompanyEstateAdService(CompanyEstateAdDAO companyEstateAdDAO, EstateCompanyDAO estateCompanyDAO) {
        this.companyEstateAdDAO = companyEstateAdDAO;
        this.estateCompanyDAO = estateCompanyDAO;
    }

    /*public List<EstateAd> getEstateAds() {
        return estateAdDAO.findAll();
    }*/

    public List<CompanyEstateAd> listAll(String keyword) {
        if (keyword != null) {
            return companyEstateAdDAO.searchByKeyword(keyword);
        } else {
            return (List<CompanyEstateAd>) companyEstateAdDAO.findAll();
        }
    }

    public EstateCompany getObjectByAgentUsername(String username) {
        return estateCompanyDAO.findByAgentUsername(username);
    }


    public CompanyEstateAd addCompanyEstateAd(CompanyEstateAdRegistrationBody companyEstateAdRegistrationBody) throws AdAlreadyExistsException {
        EstateCompany estateCompany = null;
        if (estateCompanyDAO.findByAgentUsernameIgnoreCase(companyEstateAdRegistrationBody.getCompanyAgentUsername()).isPresent()) {
            estateCompany = getObjectByAgentUsername(companyEstateAdRegistrationBody.getCompanyAgentUsername());
        } //yoksa öle bi company ne yap?

        if(companyEstateAdDAO.findByEstateTypeIgnoreCase(companyEstateAdRegistrationBody.getEstateType()).isPresent()
                && companyEstateAdDAO.findBySqrMeter(companyEstateAdRegistrationBody.getSqrMeter()).isPresent()
                && companyEstateAdDAO.findByRoomNum(companyEstateAdRegistrationBody.getRoomNum()).isPresent()
                && companyEstateAdDAO.findByFloorNum(companyEstateAdRegistrationBody.getFloorNum()).isPresent()
                && companyEstateAdDAO.findByAptFloor(companyEstateAdRegistrationBody.getAptFloor()).isPresent()
                && companyEstateAdDAO.findByHeatingTypeIgnoreCase(companyEstateAdRegistrationBody.getHeatingType()).isPresent()
                && companyEstateAdDAO.findByAdAddressIgnoreCase(companyEstateAdRegistrationBody.getAdAddress()).isPresent()) {

                throw new AdAlreadyExistsException();
        }

        CompanyEstateAd companyEstateAd = new CompanyEstateAd();
        companyEstateAd.setEstCompany(estateCompany);
        estateCompany.addToCompanyAdList(companyEstateAd);
        companyEstateAd.setEstateType(companyEstateAdRegistrationBody.getEstateType());
        companyEstateAd.setSqrMeter(companyEstateAdRegistrationBody.getSqrMeter());
        companyEstateAd.setRoomNum(companyEstateAdRegistrationBody.getRoomNum());
        companyEstateAd.setFloorNum(companyEstateAdRegistrationBody.getFloorNum());
        companyEstateAd.setAptFloor(companyEstateAdRegistrationBody.getAptFloor());
        companyEstateAd.setHeatingType(companyEstateAdRegistrationBody.getHeatingType());
        companyEstateAd.setAdAddress(companyEstateAdRegistrationBody.getAdAddress());
        companyEstateAd = companyEstateAdDAO.save(companyEstateAd);
        return companyEstateAd;
    }

}

