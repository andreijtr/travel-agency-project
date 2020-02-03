package com.sda.travel_agency.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "user.find",
                    query = "select u from User u where u.email = :email"),
        @NamedQuery(name = "user.login",
                    query = "select u from User u where u.email = :email and u.password = :password"),
        @NamedQuery(name = "user.updateAmount",
                    query = "update User u set u.totalAmount = :amount where u.id = :id")
})

@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<TripDetail> tripDetailSet = new HashSet<>();

    public User() {
    }

    public User(String email, String password, double totalAmount) {
        this.email = email;
        this.password = password;
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Double.compare(user.totalAmount, totalAmount) == 0 &&
                email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, totalAmount);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<TripDetail> getTripDetailSet() {
        return tripDetailSet;
    }

    public void setTripDetailSet(Set<TripDetail> tripDetailSet) {
        this.tripDetailSet = tripDetailSet;
    }
}
