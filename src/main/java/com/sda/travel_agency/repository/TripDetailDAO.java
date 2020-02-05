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
            
// partea asta poate sa fie o metoda separata , poate fi chiar si in clasa HotelDAO. ce faci aici nu afecteaza cu nimic
            // tranzactia, sau cel putin asa pare
            // daca ai fi vrut ca trip detail sa nu fie salvat daca hotelavailability e falise/ null 
            // iti lipseste un if (hotelAvailability= cum vrei tu ) {transaction rollback }
            // chiar si asa poti muta intr o metoda separata care sa ti returneze un HotelAvailability.. te bazezi ca getSingleResult iti returneaza
            // hibernate exception si daca e asta nu ai nici o camera... cam superficial..
            Query roomsQuery = session.createNamedQuery("hotelAvailability.find");
            roomsQuery.setParameter("hotel", trip.getHotel());
            roomsQuery.setParameter("startDate", trip.getCheckInDate());
            roomsQuery.setParameter("endDate", trip.getCheckOutDate());
            HotelAvailability hotelAvailability = (HotelAvailability) roomsQuery.getSingleResult();

            //inserez tripDetails in DB si fac update la hotelAvailability, flight seats si userAmount
            session.persist(tripDetail);

            //update user amount
            
            // din moment ce incepi o tranzactie sus si o commiti sau rollback in catch/finally
            // fiecare bucata de cod care updateaza o entitate poate sa fie pusa intr o alta metoda
            // poate fi private ca doar aici o folosesti care sa faca update ul, eventual fara sa porneasca o alta tranzactie
            // poti face toate pregatirile obiectului pe care vrei sa l updatezi intr o alta metoda si aici sa faci doar executeUpdate
            // ramai cu 2 linii de cod
            double userNewAmount = user.getTotalAmount() + tripDetail.getAmount();
            Query userAmountUpdate = session.createNamedQuery("user.updateAmount");
            userAmountUpdate.setParameter("amount", userNewAmount);
            userAmountUpdate.setParameter("id", user.getId());
            userAmountUpdate.executeUpdate();

            //tranzactia ta incepe sus si se termina
            //update rooms availability
            
            //daca diferenta de aici da < 0 ? updatezi cu numar negativ ?
            // ce inseamna sa ajungi sa updatezi cu numar negativ ??
            int newSingleRoomsAvailable = hotelAvailability.getSingleRooms() - tripDetail.getSingleRooms();
            int newDoubleRoomsAvailable = hotelAvailability.getDoubleRooms() - tripDetail.getDoubleRooms();
            int newExtraBedsAvailable = hotelAvailability.getExtraBeds() - tripDetail.getExtraBeds();

            // vezi ce am scris la update user
            Query roomsUpdate = session.createNamedQuery("hotelAvailability.updateRooms");
            roomsUpdate.setParameter("singleRooms", newSingleRoomsAvailable);
            roomsUpdate.setParameter("doubleRooms", newDoubleRoomsAvailable);
            roomsUpdate.setParameter("extraBeds", newExtraBedsAvailable);
            roomsUpdate.setParameter("id", hotelAvailability.getId());
            roomsUpdate.executeUpdate();

            //update flights available seats
            
            //vezi ce am scris la update user
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
