package com.example.projectEmlak.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EstateCompRegistrationBody {
    @NotNull
    @NotBlank
    private String companyName;

    @NotNull
    @NotBlank
    private String estateAgentFirstName;
    @NotNull
    @NotBlank
    private String estateAgentLastName;

    @NotNull
    @NotBlank
    private String compAddress;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
    private String fax;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String agentUsername;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 50)
    private String agentPassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
