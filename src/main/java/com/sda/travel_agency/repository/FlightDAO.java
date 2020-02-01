package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.Flight;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Repository
public class FlightDAO {

    public void save(Flight flight){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(flight);
        transaction.commit();
        session.close();
    }

    public Flight find(Flight flightTransient){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("flight.find");
        query.setParameter("flightNumber", flightTransient.getFlightNumber());
        query.setParameter("airport", flightTransient.getAirport());

        Flight flight = (Flight) query.getSingleResult();
        transaction.commit();
        session.close();
        return flight;
    }
}
