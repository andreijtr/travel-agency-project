package com.sda.travel_agency.logic.dto;

public class UserDTO {
    private String email;
    private double amount;
    private String password;

    public UserDTO(String email, double amount, String password) {
        this.email = email;
        this.amount = amount;
        this.password = password;
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
