package com.sda.travel_agency.logic.dto;

public class HotelDTO {

    private String name;
    private int standard;
    private String description;
    private CityDTO cityDTO;

    public HotelDTO() {
    }

    public HotelDTO(String name, int standard, String description, CityDTO cityDTO) {
        this.name = name;
        this.standard = standard;
        this.description = description;
        this.cityDTO = cityDTO;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                "name='" + name + '\'' +
                ", standard=" + standard +
                ", description='" + description + '\'' +
                ", cityDTO=" + cityDTO +
                '}';
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

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}
