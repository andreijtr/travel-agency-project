package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "airport.find",
                    query = "select a from Airport a where a.name = :name")
})

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Flight> flights = new HashSet<>();

    public Airport(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Airport() {
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return id == airport.id &&
                name.equals(airport.name) &&
                city.equals(airport.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
