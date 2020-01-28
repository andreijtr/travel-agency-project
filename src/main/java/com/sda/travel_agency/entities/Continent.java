package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name ="continent.findByName" , query = "select c from Continent c where c.name = :name")
})

@Entity
@Table(name = "continents")
public class Continent {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Country> countries = new HashSet<>();

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Continent)) return false;
        Continent continent = (Continent) o;
        return id == continent.id &&
                name.equals(continent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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

    public void addCountry(Country country) {
        countries.add(country);
        country.setContinent(this);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
        country.setContinent(null);
    }
}
