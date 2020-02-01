package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "trip.findAll",
                    query = "select t from Trip t"),

        @NamedQuery(name = "trip.find",
                    query = "select t from Trip t where " +
                            "(:fromDate is null or t.checkInDate = :fromDate) and " +
                            "(:toDate is null or t.checkOutDate = :toDate) and" +
                            "(:city is null or t.hotel.city = :city) and" +
                            "(:hotel is null or t.hotel = :hotel)")
})

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_flight_id")
    private Flight departureFlight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "return_flight_id")
    private Flight returnFlight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "start_date")
    private Date checkInDate;

    @Column(name = "end_date")
    private Date checkOutDate;

    @Column(name = "promoted")
    private Boolean isPromoted;

    public Trip() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                departureFlight.equals(trip.departureFlight) &&
                returnFlight.equals(trip.returnFlight) &&
                hotel.equals(trip.hotel) &&
                checkInDate.equals(trip.checkInDate) &&
                checkOutDate.equals(trip.checkOutDate) &&
                Objects.equals(isPromoted, trip.isPromoted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureFlight, returnFlight, hotel, checkInDate, checkOutDate, isPromoted);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", departureFlight=" + departureFlight +
                ", returnFlight=" + returnFlight +
                ", hotel=" + hotel +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", isPromoted=" + isPromoted +
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

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Flight departureFlight) {
        this.departureFlight = departureFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
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

    public Boolean getPromoted() {
        return isPromoted;
    }

    public void setPromoted(Boolean promoted) {
        isPromoted = promoted;
    }
}
