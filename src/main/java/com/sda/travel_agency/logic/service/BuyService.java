package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.*;
import com.sda.travel_agency.logic.convertor.TripConvertor;
import com.sda.travel_agency.logic.dto.TripDetailDTO;
import com.sda.travel_agency.logic.exception.NoAvailableRoomsException;
import com.sda.travel_agency.logic.exception.NoSeatsAvailableException;
import com.sda.travel_agency.repository.HotelAvailabilityDAO;
import com.sda.travel_agency.repository.TripDAO;
import com.sda.travel_agency.repository.TripDetailDAO;
import com.sda.travel_agency.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;

@Component
public class BuyService {

    @Autowired
    private TripDetailDAO tripDetailDAO;
    @Autowired
    private TripService tripService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private HotelAvailabilityService hotelAvailabilityService;
    @Autowired
    private FlightService flightService;

//    public void buy(TripDetailDTO tripDetailDTO) throws NoAvailableRoomsException, NoSeatsAvailableException {
//        int numberOfPersons = tripDetailDTO.getPersons();
//        Trip trip = tripService.findSingle(tripDetailDTO.getTripDTO());
//        User user = userDAO.find(tripDetailDTO.getUserDTO().getEmail());
//
//        Calendar calendar = Calendar.getInstance();
//        java.util.Date dateUtil = calendar.getTime();
//        java.sql.Date dateOfPurchase = new Date(dateUtil.getTime());
//
//        //aici caut camerele in perioada cand vreau sa cumpar (check method din haDAO)
//        //aceasta metoda intai verifica daca se sunt camere libere, asta arunca exceptia
//        double roomsPrice = hotelAvailabilityService.computeRoomsPrice(trip, tripDetailDTO.getSingleRooms(), tripDetailDTO.getDoubleRooms(), tripDetailDTO.getExtraBeds());
//
//        //aici caut locurile in avion (pentru ambele dus intors)
//        //aceasta metoda verificca mai intai daca sunt locuri libere altfel arunca o exceptie
//        double departureFlightPrice = flightService.computePriceForFlight(trip.getDepartureFlight(), numberOfPersons);
//        double returnFlightPrice = flightService.computePriceForFlight(trip.getReturnFlight(), numberOfPersons);
//        double totalAmount = roomsPrice + departureFlightPrice + returnFlightPrice;
//
//        //trebuie sa creezi un tripDetails, sa i setezi parametrii si sa l salvezi
//        //apoi trebuie sa modifici tot in DB la camere, zboruri si user
//        TripDetail tripDetail = new TripDetail();
//        tripDetail.setTrip(trip);
//        tripDetail.setUser(user);
//        tripDetail.setTripNumber("first buy");
//        tripDetail.setDateOfPurchase(dateOfPurchase);
//        tripDetail.setAmount(totalAmount);
//        tripDetail.setSingleRooms(tripDetailDTO.getSingleRooms());
//        tripDetail.setDoubleRooms(tripDetailDTO.getDoubleRooms());
//        tripDetail.setExtraBeds(tripDetailDTO.getExtraBeds());
//    }
}
