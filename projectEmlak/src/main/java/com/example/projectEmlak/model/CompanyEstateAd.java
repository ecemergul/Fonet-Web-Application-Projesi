package com.example.projectEmlak.model;

import jakarta.persistence.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Entity
@Table(name = "company_estate_ad")
public class CompanyEstateAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", nullable = false)
    private Long estateAdID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estate_company_id"/*, nullable = false*/)
    private EstateCompany estCompany;

    static Customer customer = null;

    @Column(name = "estate_type", nullable = false, length = 100)
    private String estateType;

    @Column(name = "square_meter", nullable = false)
    private Integer sqrMeter;

    @Column(name = "room_number")
    private Integer roomNum;

    @Column(name = "floor_number")
    private Integer floorNum;

    @Column(name = "apartment_floor")
    private Integer aptFloor;

    @Column(name = "heating_type", length = 100)
    private String heatingType;

    @Column(name = "ad_address", length = 600)
    private String adAddress;

    public CompanyEstateAd() {}

    public CompanyEstateAd(EstateCompany estCompany, String estateType, Integer sqrMeter, Integer roomNum, Integer floorNum, Integer aptFloor, String heatingType, String adAddress) {
        this.estCompany = estCompany;
        this.estateType = estateType;
        this.sqrMeter = sqrMeter;
        this.roomNum = roomNum;
        this.floorNum = floorNum;
        this.aptFloor = aptFloor;
        this.heatingType = heatingType;
        this.adAddress = adAddress;
    }

    public Long getEstateAdID() {
        return estateAdID;
    }

    public void setEstateAdID(Long estateAdID) {
        this.estateAdID = estateAdID;
    }

    public EstateCompany getEstCompany() {
        return estCompany;
    }

    public void setEstCompany(EstateCompany estCompany) {
        this.estCompany = estCompany;
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

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CompanyEstateAd.customer = customer;
    }

    public String getAdAddress() {
        return adAddress;
    }

    public void setAdAddress(String adAddress) {
        this.adAddress = adAddress;
    }

}

