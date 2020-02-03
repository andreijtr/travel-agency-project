package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.logic.convertor.HotelConvertor;
import com.sda.travel_agency.logic.convertor.TripConvertor;
import com.sda.travel_agency.logic.dto.TripDTO;
import com.sda.travel_agency.repository.CityDAO;
import com.sda.travel_agency.repository.HotelDAO;
import com.sda.travel_agency.repository.TripDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripDAO tripDAO;

    @Autowired
    private TripConvertor tripConvertor;

    @Autowired
    private HotelConvertor hotelConvertor;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private CityDAO cityDAO;

    public void save(TripDTO tripDTO) {
        Trip trip = tripConvertor.convertToTransientTrip(tripDTO);
        tripDAO.save(trip);
    }

    public List<TripDTO> findAll() {
        List<Trip> tripList = tripDAO.findAll();
        return tripConvertor.convertToDTOList(tripList);
    }

    public List<TripDTO> find(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setCheckInDate(tripDTO.getCheckInDate());
        trip.setCheckOutDate(tripDTO.getCheckOutDate());
        City city = null;
        Hotel hotel = null;
        try {
            hotel = hotelDAO.find(hotelConvertor.convertToTransientHotel(tripDTO.getHotelDTO()));
            city = hotel.getCity();
            trip.setHotel(hotel);
        } catch (NoResultException ex) {
            System.out.println("No hotel found!");
            city = cityDAO.find(tripDTO.getHotelDTO().getCityDTO());
        }

        List<Trip> tripList = tripDAO.findByTripAndCity(trip, city);
        return tripConvertor.convertToDTOList(tripList);
    }

    public Trip findSingle(TripDTO tripDTO) {
        Trip trip = tripConvertor.convertToTransientTrip(tripDTO);
        return tripDAO.find(trip);
    }
}
