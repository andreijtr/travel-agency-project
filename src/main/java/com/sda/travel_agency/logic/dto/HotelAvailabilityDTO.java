package com.sda.travel_agency.logic.dto;

import java.util.Date;

public class HotelAvailabilityDTO {
    private HotelDTO hotelDTO;
    private Date startDate;
    private Date endDate;
    private int singleRooms;
    private int doubleRooms;
    private int extraBeds;
    private double singleRoomPrice;
    private double doubleRoomPrice;
    private double extraBedPrice;

    @Override
    public String toString() {
        return "HotelAvailabilityDTO{" +
                "hotelDTO=" + hotelDTO +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", singleRooms=" + singleRooms +
                ", doubleRooms=" + doubleRooms +
                ", extraBeds=" + extraBeds +
                ", singleRoomPrice=" + singleRoomPrice +
                ", doubleRoomPrice=" + doubleRoomPrice +
                ", extraBedPrice=" + extraBedPrice +
                '}';
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSingleRooms() {
        return singleRooms;
    }

    public void setSingleRooms(int singleRooms) {
        this.singleRooms = singleRooms;
    }

    public int getDoubleRooms() {
        return doubleRooms;
    }

    public void setDoubleRooms(int doubleRooms) {
        this.doubleRooms = doubleRooms;
    }

    public int getExtraBeds() {
        return extraBeds;
    }

    public void setExtraBeds(int extraBeds) {
        this.extraBeds = extraBeds;
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
}
