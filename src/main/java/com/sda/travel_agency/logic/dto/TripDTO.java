package com.sda.travel_agency.logic.dto;

import javax.validation.constraints.*;
import java.sql.Date;

public class TripDTO {

    private FlightDTO departureFlightDTO;
    private FlightDTO returnFlightDTO;

    //these validations are for controller, so user is immediately notify is input is not valid (first level of validation)
    @NotNull(message = "City or hotel are mandatory!")
    private HotelDTO hotelDTO;

    @NotNull(message = "Please choose a start day for your trip")
    private Date checkInDate;
    private Date checkOutDate;
    private String isPromoted;

    @Min(value = 1, message = "You should set at least one person.")
    private int numberOfPersons;
    private double singleRoomPrice;
    private double doubleRoomPrice;
    private double extraBedPrice;

    public TripDTO() {
    }

    public double getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(double singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(double doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public double getExtraBedPrice() {
        return extraBedPrice;
    }

    public void setExtraBedPrice(double extraBedPrice) {
        this.extraBedPrice = extraBedPrice;
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "departureFlightDTO=" + departureFlightDTO +
                ", returnFlightDTO=" + returnFlightDTO +
                ", hotelDTO=" + hotelDTO +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", isPromoted='" + isPromoted + '\'' +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }

    public FlightDTO getDepartureFlightDTO() {
        return departureFlightDTO;
    }

    public void setDepartureFlightDTO(FlightDTO departureFlightDTO) {
        this.departureFlightDTO = departureFlightDTO;
    }

    public FlightDTO getReturnFlightDTO() {
        return returnFlightDTO;
    }

    public void setReturnFlightDTO(FlightDTO returnFlightDTO) {
        this.returnFlightDTO = returnFlightDTO;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getIsPromoted() {
        return isPromoted;
    }

    public void setIsPromoted(String isPromoted) {
        this.isPromoted = isPromoted;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
