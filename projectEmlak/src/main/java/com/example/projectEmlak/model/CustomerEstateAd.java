package com.example.projectEmlak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_estate_ad")
public class CustomerEstateAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id", nullable = false)
    private Long estateAdID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id"/*, nullable = false*/)
    private Customer customer;

    static EstateCompany estCompany = null;

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

    public CustomerEstateAd() {}

    public CustomerEstateAd(Customer customer, String estateType, Integer sqrMeter, Integer roomNum, Integer floorNum, Integer aptFloor, String heatingType, String adAddress) {
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public static EstateCompany getEstCompany() {
        return estCompany;
    }

    public static void setEstCompany(EstateCompany estCompany) {
        CustomerEstateAd.estCompany = estCompany;
    }

    public String getAdAddress() {
        return adAddress;
    }

    public void setAdAddress(String adAddress) {
        this.adAddress = adAddress;
    }
}
