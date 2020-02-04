package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.entities.TripDetail;
import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.logic.convertor.TripDetailConvertor;
import com.sda.travel_agency.logic.dto.TripDetailDTO;
import com.sda.travel_agency.logic.exception.NoAvailableRoomsException;
import com.sda.travel_agency.logic.exception.NoSeatsAvailableException;
import com.sda.travel_agency.repository.TripDetailDAO;
import com.sda.travel_agency.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
public class TripDetailService {

    @Autowired
    private TripDetailConvertor tripDetailConvertor;

    @Autowired
    private HotelAvailabilityService hotelAvailabilityService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TripDetailDAO tripDetailDAO;

    public String save(TripDetailDTO tripDetailDTO) {
        int numberOfPersons = tripDetailDTO.getPersons();
        TripDetail tripDetail = tripDetailConvertor.convertToTransientTripDetail(tripDetailDTO);
        Trip trip = tripDetail.getTrip();
        User user = tripDetail.getUser();

        double roomsPrice = 0.0;
        try {
            hotelAvailabilityService.checkRooms(trip, tripDetail.getSingleRooms(), tripDetail.getDoubleRooms(), tripDetail.getExtraBeds());
            roomsPrice = hotelAvailabilityService.computeRoomsPrice(trip, tripDetail.getSingleRooms(), tripDetail.getDoubleRooms(), tripDetail.getExtraBeds());
        } catch (NoAvailableRoomsException roomsEx) {
            return roomsEx.getMessage();
        }

        double flightsPrice = 0.0;
        try {
            flightService.checkSeats(trip.getDepartureFlight(), numberOfPersons);
            flightService.checkSeats(trip.getReturnFlight(), numberOfPersons);
            flightsPrice = (trip.getDepartureFlight().getPrice() + trip.getReturnFlight().getPrice()) * numberOfPersons;
        } catch (NoSeatsAvailableException seatsEx) {
            return seatsEx.getMessage();
        }

        double totalPrice = roomsPrice + flightsPrice;
        totalPrice = UserUtil.applyDiscount(tripDetail.getUser(), totalPrice);

        //necesar pt data comenzii
        Calendar calendar = Calendar.getInstance();
        java.util.Date dateUtil = calendar.getTime();
        java.sql.Date dateOfPurchase = new Date(dateUtil.getTime());

        tripDetail.setAmount(totalPrice);
        tripDetail.setDateOfPurchase(dateOfPurchase);

        return tripDetailDAO.save(tripDetail, numberOfPersons);
    }
}
