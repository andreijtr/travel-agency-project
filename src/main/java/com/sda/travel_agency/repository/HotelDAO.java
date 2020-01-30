package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.logic.dto.HotelDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {

    public void save(Hotel hotel) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(hotel);
        transaction.commit();
        session.close();
    }

    public List<Hotel> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("hotel.findAll");
        List<Hotel> hotelList = query.getResultList();
        transaction.commit();
        session.close();
        return hotelList;
    }

    public Hotel find(HotelDTO hotelDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("hotel.find");
        query.setParameter("hotelName", hotelDTO.getName());
        query.setParameter("cityName", hotelDTO.getCityDTO().getName());

        Hotel hotel = (Hotel) query.getSingleResult();
        transaction.commit();
        session.close();
        return hotel;
    }
}
