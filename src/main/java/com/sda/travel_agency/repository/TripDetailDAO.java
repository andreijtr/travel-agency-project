package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.entities.TripDetail;
import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.logic.exception.NoAvailableRoomsException;
import com.sda.travel_agency.logic.exception.NoSeatsAvailableException;
import com.sda.travel_agency.util.Consts;
import com.sda.travel_agency.util.FlightUtil;
import com.sda.travel_agency.util.RoomsUtil;
import com.sda.travel_agency.util.UserUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Date;
import java.util.Calendar;

@Repository
public class TripDetailDAO {

    public String save(TripDetail tripDetail, int numberOfPersons) {
        Trip trip = tripDetail.getTrip();
        User user = tripDetail.getUser();

        //all operations for buying a trip must happen in the same transaction
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            //aici calculez pretul pe cazare in perioada cand vreau sa cumpar
            //daca nu arunca o exceptie, calculez pretul cazarii
            Query roomsQuery = session.createNamedQuery("hotelAvailability.find");
            roomsQuery.setParameter("hotel", trip.getHotel());
            roomsQuery.setParameter("startDate", trip.getCheckInDate());
            roomsQuery.setParameter("endDate", trip.getCheckOutDate());
            HotelAvailability hotelAvailability = (HotelAvailability) roomsQuery.getSingleResult();

            double roomsPrice = 0.0;
            try {
                RoomsUtil.checkAvailability(hotelAvailability, tripDetail.getSingleRooms(), tripDetail.getDoubleRooms(), tripDetail.getExtraBeds());
                roomsPrice = RoomsUtil.computePrice(hotelAvailability, tripDetail.getSingleRooms(), tripDetail.getDoubleRooms(), tripDetail.getExtraBeds());
            } catch (NoAvailableRoomsException roomsEx) {
                return roomsEx.getMessage();
            }

            //aici verific locurile in avion (pentru ambele zboruri dus intors)
            //daca nu arunca o exceptie, calculez pretul biletelor de avion
            double flightsPrice = 0.0;
            try {
                FlightUtil.checkSeats(trip.getDepartureFlight(), numberOfPersons);
                FlightUtil.checkSeats(trip.getReturnFlight(), numberOfPersons);
                flightsPrice = (trip.getDepartureFlight().getPrice() + trip.getReturnFlight().getPrice()) * numberOfPersons;
            } catch (NoSeatsAvailableException seatsEx) {
                return seatsEx.getMessage();
            }

            //calculez pretul total acum si aplic discount
            double totalPrice = roomsPrice + flightsPrice;
            totalPrice = UserUtil.applyDiscount(tripDetail.getUser(), totalPrice);

            //necesar pt data comenzii
            Calendar calendar = Calendar.getInstance();
            java.util.Date dateUtil = calendar.getTime();
            java.sql.Date dateOfPurchase = new Date(dateUtil.getTime());

            tripDetail.setAmount(totalPrice);
            tripDetail.setDateOfPurchase(dateOfPurchase);
            //aici trebuie modificat
            tripDetail.setTripNumber("9876543");

            //inserez tripDetails in DB si fac update la hotelAvailability, flight seats si userAmount
            session.save(tripDetail);

            //update user amount
            double userNewAmount = user.getTotalAmount() + totalPrice;
            Query userAmountUpdate = session.createNamedQuery("user.updateAmount");
            userAmountUpdate.setParameter("amount", userNewAmount);
            userAmountUpdate.setParameter("id", user.getId());
            userAmountUpdate.executeUpdate();

            //update rooms availability
            int newSingleRoomsAvailable = hotelAvailability.getSingleRooms() - tripDetail.getSingleRooms();
            int newDoubleRoomsAvailable = hotelAvailability.getDoubleRooms() - tripDetail.getDoubleRooms();
            int newExtraBedsAvailable = hotelAvailability.getExtraBeds() - tripDetail.getExtraBeds();
            Query roomsUpdate = session.createNamedQuery("hotelAvailability.updateRooms");
            roomsUpdate.setParameter("singleRooms", newSingleRoomsAvailable);
            roomsUpdate.setParameter("doubleRooms", newDoubleRoomsAvailable);
            roomsUpdate.setParameter("extraBeds", newExtraBedsAvailable);
            roomsUpdate.setParameter("id", hotelAvailability.getId());
            roomsUpdate.executeUpdate();

            //update flights available seats
            int departureFlightSeats = trip.getDepartureFlight().getAvailableSeats() - numberOfPersons;
            Query departureFlightUpdate = session.createNamedQuery("flight.updateAvailableSeats");
            departureFlightUpdate.setParameter("availableSeats", departureFlightSeats);
            departureFlightUpdate.setParameter("id", trip.getDepartureFlight().getId());
            departureFlightUpdate.executeUpdate();

            int returnFlightSeats = trip.getReturnFlight().getAvailableSeats() - numberOfPersons;
            Query returnFlightUpdate = session.createNamedQuery("flight.updateAvailableSeats");
            returnFlightUpdate.setParameter("availableSeats", returnFlightSeats);
            returnFlightUpdate.setParameter("id", trip.getReturnFlight().getId());
            returnFlightUpdate.executeUpdate();

            transaction.commit();
        } catch (HibernateException hibernateEx) {
            if (transaction != null) {
                transaction.rollback();
                hibernateEx.printStackTrace();
            }
        } finally {
            session.close();
        }

        return Consts.BUY_SUCCESSFUL + "\n" + Consts.TRIP_NUMBER + tripDetail.getTripNumber();
    }
}
