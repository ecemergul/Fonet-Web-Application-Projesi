package com.example.projectEmlak.api.model;

import com.example.projectEmlak.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CustomerEstateAdRegistrationBody {
    @NotNull
    @NotBlank
    private String customerUsername;

    //static String companyAgentUsername = null;

    @NotNull
    @NotBlank
    private String estateType;
    @NotNull
    @Positive
    private Integer sqrMeter;
    private Integer roomNum;

    private Integer floorNum;

    private Integer aptFloor;

    private String heatingType;

    private String adAddress;

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getEstateType() {
        return estateType;
    }

    public void setEstateType(String estateType) {
        this.estateType = estateType;
    }

    public Integer getSqrMeter() {
        return sqrMeter;
    }

    public void setSqrMeter(Integer sqrMeter) {
        this.sqrMeter = sqrMeter;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getAptFloor() {
        return aptFloor;
    }

    public void setAptFloor(Integer aptFloor) {
        this.aptFloor = aptFloor;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public String getAdAddress() {
        return adAddress;
    }

    public void setAdAddress(String adAddress) {
        this.adAddress = adAddress;
    }

    /*public static String getCompanyAgentUsername() {
        return companyAgentUsername;
    }

    public static void setCompanyAgentUsername(String companyAgentUsername) {
        CustomerEstateAdRegistrationBody.companyAgentUsername = companyAgentUsername;
    }*/


}
