package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "hotel.findAll",
                    query = "select h from Hotel h"),
        @NamedQuery(name = "hotel.find",
                    query = "select h from Hotel h where h.name = :hotelName and h.city.name = :cityName")
})

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "standard")
    private int standard;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private Set<HotelAvailability> hotelAvailabilitySet = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Trip> tripSet = new HashSet<>();

    public Hotel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                standard == hotel.standard &&
                name.equals(hotel.name) &&
                description.equals(hotel.description) &&
                city.equals(hotel.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, standard, description, city);
    }

//    @Override
//    public String toString() {
//        return "Hotel{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", standard=" + standard +
//                ", description='" + description + '\'' +
//                ", city=" + city +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Transient
    public void addRoom(HotelAvailability room) {
        this.hotelAvailabilitySet.add(room);
        room.setHotel(this);
    }

    @Transient
    public void removeRoom(HotelAvailability room) {
        this.hotelAvailabilitySet.remove(room);
        room.setHotel(null);
    }

    public Set<HotelAvailability> getHotelAvailabilitySet() {
        return hotelAvailabilitySet;
    }

    public void setHotelAvailabilitySet(Set<HotelAvailability> hotelAvailabilitySet) {
        this.hotelAvailabilitySet = hotelAvailabilitySet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Trip> getTripSet() {
        return tripSet;
    }

    public void setTripSet(Set<Trip> tripSet) {
        this.tripSet = tripSet;
    }
}
