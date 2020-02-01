package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.Airport;
import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.logic.dto.AirportDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class AirportDAO {

    public void save(Airport airport){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(airport);
        transaction.commit();
        session.close();
    }

    public Airport find(String airportName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("airport.find");
        query.setParameter("name", airportName);
        Airport airport = (Airport) query.getSingleResult();

        transaction.commit();
        session.close();
        return airport;
    }
}
