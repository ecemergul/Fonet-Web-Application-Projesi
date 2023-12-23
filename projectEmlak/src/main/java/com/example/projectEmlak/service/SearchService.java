package com.example.projectEmlak.service;

import com.example.projectEmlak.model.CompanyEstateAd;
import com.example.projectEmlak.model.CustomerEstateAd;
import com.example.projectEmlak.model.dao.CompanyEstateAdDAO;
import com.example.projectEmlak.model.dao.CustomerEstateAdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private CustomerEstateAdDAO customerEstateAdDAO;
    private CompanyEstateAdDAO companyEstateAdDAO;

    @Autowired
    public SearchService(CustomerEstateAdDAO customerEstateAdDAO, CompanyEstateAdDAO companyEstateAdDAO) {
        this.customerEstateAdDAO = customerEstateAdDAO;
        this.companyEstateAdDAO = companyEstateAdDAO;
    }

    public List<Object> searchEntitiesByKeyword(String keyword) {
        List<Object> combinedResults = new ArrayList<>();

        List<CustomerEstateAd> customerEstateAdResults = customerEstateAdDAO.searchByKeyword(keyword);
        List<CompanyEstateAd> companyEstateAdResults = companyEstateAdDAO.searchByKeyword(keyword);

        combinedResults.addAll(customerEstateAdResults);
        combinedResults.addAll(companyEstateAdResults);

        return combinedResults;
    }
}
