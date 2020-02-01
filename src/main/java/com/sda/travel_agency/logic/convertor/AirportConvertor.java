package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.Airport;
import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.logic.dto.AirportDTO;
import com.sda.travel_agency.logic.dto.CityDTO;
import com.sda.travel_agency.repository.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportConvertor {

    @Autowired
    private CityConvertor cityConvertor;

    @Autowired
    private CityDAO cityDAO;

    public AirportDTO convertEntityToDTO(Airport airport) {
        CityDTO cityDTO = cityConvertor.convertCityToCityDTO(airport.getCity());
        return new AirportDTO(airport.getName(), cityDTO);
    }

    public Airport convertDTOToTransientObject(AirportDTO airportDTO) {
        City city = cityDAO.find(airportDTO.getCityDTO());
        return new Airport(airportDTO.getName(), city);
    }
}
