package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.entities.TripDetail;
import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.util.Consts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

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

            Query roomsQuery = session.createNamedQuery("hotelAvailability.find");
            roomsQuery.setParameter("hotel", trip.getHotel());
            roomsQuery.setParameter("startDate", trip.getCheckInDate());
            roomsQuery.setParameter("endDate", trip.getCheckOutDate());
            HotelAvailability hotelAvailability = (HotelAvailability) roomsQuery.getSingleResult();

            //inserez tripDetails in DB si fac update la hotelAvailability, flight seats si userAmount
            session.persist(tripDetail);

            //update user amount
            double userNewAmount = user.getTotalAmount() + tripDetail.getAmount();
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
