package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Flight;
import com.sda.travel_agency.logic.convertor.FlightConvertor;
import com.sda.travel_agency.logic.dto.FlightDTO;
import com.sda.travel_agency.logic.exception.NoSeatsAvailableException;
import com.sda.travel_agency.repository.FlightDAO;
import com.sda.travel_agency.util.Consts;
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

    public void checkSeats(Flight flight, int numberOfSeats) throws NoSeatsAvailableException {
        if (flight.getAvailableSeats() <= numberOfSeats) {
            throw new NoSeatsAvailableException(Consts.FLIGHT_NO_SEATS);
        }
    }

    public double computePriceForFlight(Flight flight, int numberOfSeats) throws NoSeatsAvailableException {
        checkSeats(flight, numberOfSeats);
        return flight.getPrice() * numberOfSeats;
    }
}
