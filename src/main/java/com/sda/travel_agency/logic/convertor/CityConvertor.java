package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.entities.Country;
import com.sda.travel_agency.logic.dto.CityDTO;
import com.sda.travel_agency.logic.dto.CountryDTO;
import com.sda.travel_agency.repository.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityConvertor {

    @Autowired
    private CountryConvertor countryConvertor;

    @Autowired
    private CountryDAO countryDAO;

    public City convertCityDTOToCity(CityDTO cityDTO) {
        Country country = countryDAO.findByName(cityDTO.getCountryDTO().getName());
        City city = new City();
        city.setName(cityDTO.getName());
        city.setCountry(country);
        return city;
    }

    public CityDTO convertCityToCityDTO(City city) {
        CountryDTO countryDTO = countryConvertor.convertCountryToCountryDTO(city.getCountry());
        return new CityDTO(city.getName(), countryDTO);
    }
}
