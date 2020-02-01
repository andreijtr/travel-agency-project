package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Flight;
import com.sda.travel_agency.logic.convertor.FlightConvertor;
import com.sda.travel_agency.logic.dto.FlightDTO;
import com.sda.travel_agency.repository.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    private FlightDAO flightDAO;

    @Autowired
    private FlightConvertor flightConvertor;

    public void save(FlightDTO flightDTO){
        Flight transientFlight = flightConvertor.convertToTransientFlight(flightDTO);
        flightDAO.save(transientFlight);
    }
}
