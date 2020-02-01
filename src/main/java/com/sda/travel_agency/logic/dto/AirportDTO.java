package com.sda.travel_agency.logic.dto;

import java.util.LinkedList;
import java.util.List;

public class AirportDTO {

    private String name;
    private CityDTO cityDTO;

    public AirportDTO(String name, CityDTO cityDTO) {
        this.name = name;
        this.cityDTO = cityDTO;
    }

    public AirportDTO() {
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "name='" + name + '\'' +
                ", cityDTO=" + cityDTO +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}
