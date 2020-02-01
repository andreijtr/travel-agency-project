package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.Flight;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.logic.dto.FlightDTO;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.logic.dto.TripDTO;
import com.sda.travel_agency.repository.FlightDAO;
import com.sda.travel_agency.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TripConvertor {

    @Autowired
    private FlightConvertor flightConvertor;
    @Autowired
    private HotelConvertor hotelConvertor;
    @Autowired
    private HotelDAO hotelDAO;
    @Autowired
    private FlightDAO flightDAO;

    public TripDTO convertToDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        FlightDTO departureFlightDTO = flightConvertor.convertToDTO(trip.getDepartureFlight());
        FlightDTO returnFlightDTO = flightConvertor.convertToDTO(trip.getReturnFlight());
        HotelDTO hotelDTO = hotelConvertor.convertToDTO(trip.getHotel());

        tripDTO.setDepartureFlightDTO(departureFlightDTO);
        tripDTO.setReturnFlightDTO(returnFlightDTO);
        tripDTO.setHotelDTO(hotelDTO);
        tripDTO.setCheckInDate(trip.getCheckInDate());
        tripDTO.setCheckOutDate(trip.getCheckOutDate());
        tripDTO.setIsPromoted(trip.getPromoted().toString());

        return tripDTO;
    }

    public Trip convertToTransientTrip(TripDTO tripDTO) {
        Hotel hotelTransient = hotelConvertor.convertToTransientHotel(tripDTO.getHotelDTO());
        Hotel hotel = hotelDAO.find(hotelTransient);
        Flight departureFlightTransient = flightConvertor.convertToTransientFlight(tripDTO.getDepartureFlightDTO());
        Flight departureFlight = flightDAO.find(departureFlightTransient);
        Flight returnFlightTransient = flightConvertor.convertToTransientFlight(tripDTO.getReturnFlightDTO());
        Flight returnFlight = flightDAO.find(returnFlightTransient);

        Trip trip = new Trip();
        trip.setDepartureFlight(departureFlight);
        trip.setReturnFlight(returnFlight);
        trip.setHotel(hotel);
        trip.setCheckInDate(tripDTO.getCheckInDate());
        trip.setCheckOutDate(tripDTO.getCheckOutDate());

        if (tripDTO.getIsPromoted() == "true") {
            trip.setPromoted(true);
        } else {
            trip.setPromoted(false);
        }

        return trip;
    }

    public List<TripDTO> convertToDTOList(List<Trip> tripList){
        List<TripDTO> tripDTOList = new LinkedList<>();

        for (Trip trip : tripList) {
            TripDTO tripDTO = convertToDTO(trip);
            tripDTOList.add(tripDTO);
        }

        return tripDTOList;
    }
}
