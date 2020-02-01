package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.Airport;
import com.sda.travel_agency.entities.Flight;
import com.sda.travel_agency.logic.dto.AirportDTO;
import com.sda.travel_agency.logic.dto.FlightDTO;
import com.sda.travel_agency.repository.AirportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightConvertor {

    @Autowired
    private AirportConvertor airportConvertor;

    @Autowired
    private AirportDAO airportDAO;

    public FlightDTO convertToDTO(Flight flight){
        FlightDTO flightDTO = new FlightDTO();
        AirportDTO airportDTO = airportConvertor.convertEntityToDTO(flight.getAirport());

        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setTotalSeats(flight.getTotalSeats());
        flightDTO.setAvailableSeats(flight.getAvailableSeats());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setAirportDTO(airportDTO);

        return flightDTO;
    }

    public Flight convertToTransientFlight(FlightDTO flightDTO){
        Flight flight = new Flight();
        Airport airport = airportDAO.find(flightDTO.getAirportDTO().getName());

        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setTotalSeats(flightDTO.getTotalSeats());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setPrice(flightDTO.getPrice());
        flight.setAirport(airport);

        return flight;
    }
}
