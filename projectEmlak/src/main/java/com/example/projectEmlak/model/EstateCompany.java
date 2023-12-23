package com.example.projectEmlak.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estate_company")
public class EstateCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long estCompanyID;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "estate_agent_firstName", nullable = false, length = 100)
    private String estateAgentFirstName;

    @Column(name = "estate_agent_lastName", nullable = false, length = 100)
    private String estateAgentLastName;

    @Column(name = "address", nullable = false, length = 2000)
    private String compAddress;

    @Column(name = "phone", nullable = false, length = 60)  //unique=true mu yapsam?
    private String phone;

    @Column(name = "fax", nullable = false, length = 80) //unique=true mu yapsam?
    private String fax;

    @Column(name = "estate_agent_email", nullable = false, unique = true, length = 320)
    private String agentEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estCompany")
    private List<CompanyEstateAd> adList;

    @Column(name = "agent_username", nullable = false, unique = true)
    private String agentUsername;

    @Column(name = "agent_password", nullable = false, length = 100)
    private String agentPassword;

    public EstateCompany() {}

    public EstateCompany(String companyName, String estateAgentFirstName, String estateAgentLastName, String compAddress, String phone, String fax, String agentEmail,String agentUsername, String agentPassword) {
        this.companyName = companyName;
        this.estateAgentFirstName = estateAgentFirstName;
        this.estateAgentLastName = estateAgentLastName;
        this.compAddress = compAddress;
        this.phone = phone;
        this.fax = fax;
        this.agentEmail = agentEmail;
        this.agentUsername = agentUsername;
        this.agentPassword = agentPassword;
    }

    public EstateCompany(String companyName, String estateAgentFirstName, String estateAgentLastName, String compAddress, String phone, List<CompanyEstateAd> adList, String fax, String agentEmail, String agentUsername, String agentPassword) {
        this.companyName = companyName;
        this.estateAgentFirstName = estateAgentFirstName;
        this.estateAgentLastName = estateAgentLastName;
        this.compAddress = compAddress;
        this.phone = phone;
        this.adList = adList;
        this.fax = fax;
        this.agentEmail = agentEmail;
        this.agentUsername = agentUsername;
        this.agentPassword = agentPassword;
    }

    public Long getEstCompanyID() {
        return estCompanyID;
    }

    public void setEstCompanyID(Long estCompanyID) {
        this.estCompanyID = estCompanyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEstateAgentFirstName() {
        return estateAgentFirstName;
    }

    public void setEstateAgentFirstName(String estateAgentFirstName) {
        this.estateAgentFirstName = estateAgentFirstName;
    }

    public String getEstateAgentLastName() {
        return estateAgentLastName;
    }

    public void setEstateAgentLastName(String estateAgentLastName) {
        this.estateAgentLastName = estateAgentLastName;
    }

    public String getCompAddress() {
        return compAddress;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public List<CompanyEstateAd> getAdList() {
        return adList;
    }

    public void addToCompanyAdList(CompanyEstateAd companyEstateAd) { this.adList.add(companyEstateAd);}

    public void setAdList(List<CompanyEstateAd> adList) {
        this.adList = adList;
    }

    public String getAgentUsername() {
        return agentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }

    public String getAgentPassword() {
        return agentPassword;
    }

    public void setAgentPassword(String agentPassword) {
        this.agentPassword = agentPassword;
    }
}
