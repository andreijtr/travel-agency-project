package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.entities.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

@Repository
public class TripDAO {

    public void save(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(trip);
        transaction.commit();
        session.close();
    }

    public List<Trip> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("trip.findAll");
        List<Trip> tripList = query.getResultList();
        transaction.commit();
        session.close();
        return tripList;
    }

    public List<Trip> find(Trip trip, City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("trip.find");
        query.setParameter("fromDate", trip.getCheckInDate());
        query.setParameter("toDate", trip.getCheckOutDate());
        query.setParameter("city", city);
        query.setParameter("hotel", trip.getHotel());
        List<Trip> tripList = query.getResultList();

        transaction.commit();
        session.close();
        return tripList;
    }
}
