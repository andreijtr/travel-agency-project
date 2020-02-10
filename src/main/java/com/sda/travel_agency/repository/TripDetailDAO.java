package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.*;
import com.sda.travel_agency.util.Consts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class TripDetailDAO {

    @Autowired
    private HotelAvailabilityDAO hotelAvailabilityDAO;

    public String save(TripDetail tripDetail, int numberOfPersons) {
        Trip trip = tripDetail.getTrip();
        User user = tripDetail.getUser();
        HotelAvailability hotelAvailability = hotelAvailabilityDAO.find(trip);

        //all operations for buying a trip must happen in the same transaction
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(tripDetail);
            updateUserAmount(user, tripDetail.getAmount(), session);
            updateRooms(hotelAvailability, tripDetail.getSingleRooms(), tripDetail.getDoubleRooms(), tripDetail.getExtraBeds(), session);
            updateFlightSeats(trip.getDepartureFlight(), numberOfPersons, session);
            updateFlightSeats(trip.getReturnFlight(), numberOfPersons, session);

            transaction.commit();
        } catch (HibernateException hibernateEx) {
            if (transaction != null) {
                transaction.rollback();
                hibernateEx.printStackTrace();
                return hibernateEx.getMessage();
            }
        } finally {
            session.close();
        }
        return Consts.BUY_SUCCESSFUL + "\n" + Consts.TRIP_NUMBER + tripDetail.getTripNumber();
    }

    //I made these method private because this is the only place where I need these methods to be called in same
    //transaction
    private void updateUserAmount(User user, double tripAmount, Session session) {
        double userNewAmount = user.getTotalAmount() + tripAmount;
        Transaction transaction = session.getTransaction();
        if (transaction.isActive()) {
            Query userAmountUpdate = session.createNamedQuery("user.updateAmount");
            userAmountUpdate.setParameter("amount", userNewAmount);
            userAmountUpdate.setParameter("id", user.getId());
            userAmountUpdate.executeUpdate();
        }
    }

    private void updateRooms(HotelAvailability rooms, int singleRooms, int doubleRooms, int extraBed, Session session) {
        int newSingleRoomsAvailable = rooms.getSingleRooms() - singleRooms;
        int newDoubleRoomsAvailable = rooms.getDoubleRooms() - doubleRooms;
        int newExtraBedsAvailable = rooms.getExtraBeds() - extraBed;

        Transaction transaction = session.getTransaction();
        if (transaction.isActive()) {
            Query roomsUpdate = session.createNamedQuery("hotelAvailability.updateRooms");
            roomsUpdate.setParameter("singleRooms", newSingleRoomsAvailable);
            roomsUpdate.setParameter("doubleRooms", newDoubleRoomsAvailable);
            roomsUpdate.setParameter("extraBeds", newExtraBedsAvailable);
            roomsUpdate.setParameter("id", rooms.getId());
            roomsUpdate.executeUpdate();
        }
    }

    private void updateFlightSeats(Flight flight, int numberOfPersons, Session session) {
        int newFlightSeats = flight.getAvailableSeats() - numberOfPersons;
        Transaction transaction = session.getTransaction();
        if (transaction.isActive()) {
            Query flightUpdate = session.createNamedQuery("flight.updateAvailableSeats");
            flightUpdate.setParameter("availableSeats", newFlightSeats);
            flightUpdate.setParameter("id", flight.getId());
            flightUpdate.executeUpdate();
        }
    }
}
