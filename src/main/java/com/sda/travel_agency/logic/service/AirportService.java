package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Airport;
import com.sda.travel_agency.logic.convertor.AirportConvertor;
import com.sda.travel_agency.logic.dto.AirportDTO;
import com.sda.travel_agency.repository.AirportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportDAO airportDAO;

    @Autowired
    private AirportConvertor airportConvertor;

    public void save(AirportDTO airportDTO){
        Airport airport = airportConvertor.convertDTOToTransientObject(airportDTO);
        airportDAO.save(airport);
    }
}
