package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "city.findByNameAndCountry",
                    query = "select c from City c where c.name = :cityName and c.country.name = :countryName")
})

@Entity
@Table(name = "cities")
public class City {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Hotel> hotels = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Airport> airports = new HashSet<>();

    public City (String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return id == city.id &&
                name.equals(city.name) &&
                country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
