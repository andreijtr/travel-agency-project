package com.sda.travel_agency.logic.dto;

import java.sql.Date;

public class TripDetailDTO {
    private TripDTO tripDTO;
    private UserDTO userDTO;
    private Date dateOfPurchase;
    private String tripNumber;
    private int doubleRooms;
    private int singleRooms;
    private int extraBeds;
    private int amount;
    private int persons;

    public TripDetailDTO() {
    }

    @Override
    public String toString() {
        return "TripDetailDTO{" +
                "tripDTO=" + tripDTO +
                ", userDTO=" + userDTO +
                ", dateOfPurchase=" + dateOfPurchase +
                ", tripNumber='" + tripNumber + '\'' +
                ", doubleRooms=" + doubleRooms +
                ", singleRooms=" + singleRooms +
                ", extraBeds=" + extraBeds +
                ", amount=" + amount +
                ", persons=" + persons +
                '}';
    }

    public TripDTO getTripDTO() {
        return tripDTO;
    }

    public void setTripDTO(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public int getDoubleRooms() {
        return doubleRooms;
    }

    public void setDoubleRooms(int doubleRooms) {
        this.doubleRooms = doubleRooms;
    }

    public int getSingleRooms() {
        return singleRooms;
    }

    public void setSingleRooms(int singleRooms) {
        this.singleRooms = singleRooms;
    }

    public int getExtraBeds() {
        return extraBeds;
    }

    public void setExtraBeds(int extraBeds) {
        this.extraBeds = extraBeds;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
}
