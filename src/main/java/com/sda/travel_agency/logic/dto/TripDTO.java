package com.sda.travel_agency.logic.dto;

import java.sql.Date;

public class TripDTO {
    private FlightDTO departureFlightDTO;
    private FlightDTO returnFlightDTO;
    private HotelDTO hotelDTO;
    private Date checkInDate;
    private Date checkOutDate;
    private String isPromoted;

    public TripDTO() {
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "departureflightDTO=" + departureFlightDTO +
                ", returnFlightDTO=" + returnFlightDTO +
                ", hotelDTO=" + hotelDTO +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", isPromoted=" + isPromoted +
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
}
