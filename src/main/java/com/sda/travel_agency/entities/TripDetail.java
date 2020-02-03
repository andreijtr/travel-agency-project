package com.sda.travel_agency.entities;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "trip_details")
public class TripDetail {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;

    @Column(name = "trip_number")
    private String tripNumber;

    @Column(name = "double_rooms")
    private int doubleRooms;

    @Column(name = "single_rooms")
    private int singleRooms;

    @Column(name = "extra_beds")
    private int extraBeds;

    @Column(name = "amount")
    private double amount;

    public TripDetail() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripDetail)) return false;
        TripDetail that = (TripDetail) o;
        return id == that.id &&
                doubleRooms == that.doubleRooms &&
                singleRooms == that.singleRooms &&
                extraBeds == that.extraBeds &&
                Double.compare(that.amount, amount) == 0 &&
                trip.equals(that.trip) &&
                user.equals(that.user) &&
                dateOfPurchase.equals(that.dateOfPurchase) &&
                tripNumber.equals(that.tripNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trip, user, dateOfPurchase, tripNumber);
    }

    @Override
    public String toString() {
        return "TripDetail{" +
                "id=" + id +
                ", trip=" + trip +
                ", user=" + user +
                ", dateOfPurchase=" + dateOfPurchase +
                ", tripNumber='" + tripNumber + '\'' +
                ", doubleRooms=" + doubleRooms +
                ", singleRooms=" + singleRooms +
                ", extraBeds=" + extraBeds +
                ", amount=" + amount +
                '}';
    }

    public int getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
