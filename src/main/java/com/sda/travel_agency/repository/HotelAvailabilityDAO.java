package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.entities.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class HotelAvailabilityDAO {

    public void save(HotelAvailability hotelAvailability) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(hotelAvailability);
        transaction.commit();
        session.close();
    }

    public HotelAvailability find(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("hotelAvailability.find");
        query.setParameter("hotel", trip.getHotel());
        query.setParameter("startDate", trip.getCheckInDate());
        query.setParameter("endDate", trip.getCheckOutDate());
        HotelAvailability hotelAvailability = (HotelAvailability) query.getSingleResult();
        transaction.commit();
        session.close();
        return hotelAvailability;
    }
}
