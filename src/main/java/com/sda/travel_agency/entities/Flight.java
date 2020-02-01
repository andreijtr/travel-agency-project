package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "flight.find",
                    query = "select f from Flight f where f.flightNumber = :flightNumber and f.departureDate = :departureDate and f.airport = :airport")
})

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    private int id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Flight() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                totalSeats == flight.totalSeats &&
                availableSeats == flight.availableSeats &&
                Double.compare(flight.price, price) == 0 &&
                flightNumber.equals(flight.flightNumber) &&
                departureDate.equals(flight.departureDate) &&
                airport.equals(flight.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureDate, totalSeats, availableSeats, price, airport);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                ", airport=" + airport +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}
