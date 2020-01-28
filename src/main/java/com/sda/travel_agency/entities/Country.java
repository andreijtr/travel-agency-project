package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "country.findByContinent",
                    query = "select c from Country c where c.continent = :continent"),
        @NamedQuery(name = "country.findByName",
                    query = "select c from Country c where c.name = :name")
})

@Entity
@Table(name = "countries")
public class Country {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent=" + continent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return id == country.id &&
                name.equals(country.name) &&
                continent.equals(country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, continent);
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
