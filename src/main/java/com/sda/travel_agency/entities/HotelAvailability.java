package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class HotelAvailability {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "number_available_single_rooms")
    private int singleRooms;

    @Column(name = "number_available_double_rooms")
    private int doubleRooms;

    @Column(name = "number_available_extra_beds")
    private int extraBeds;

    @Column(name = "single_room_price")
    private double singleRoomPrice;

    @Column(name = "double_room_price")
    private double doubleRoomPrice;

    @Column(name = "extra_bed_price")
    private double extraBedPrice;

    public HotelAvailability() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelAvailability)) return false;
        HotelAvailability that = (HotelAvailability) o;
        return id == that.id &&
                singleRooms == that.singleRooms &&
                doubleRooms == that.doubleRooms &&
                extraBeds == that.extraBeds &&
                Double.compare(that.singleRoomPrice, singleRoomPrice) == 0 &&
                Double.compare(that.doubleRoomPrice, doubleRoomPrice) == 0 &&
                Double.compare(that.extraBedPrice, extraBedPrice) == 0 &&
                hotel.equals(that.hotel) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotel, startDate, endDate, singleRooms, doubleRooms, extraBeds, singleRoomPrice, doubleRoomPrice, extraBedPrice);
    }

    @Override
    public String toString() {
        return "HotelAvailability{" +
                "id=" + id +
                ", hotel=" + hotel +
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

    public int getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
