package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.HotelAvailability;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class HotelAvailabilityDAO {

    public void save(HotelAvailability hotelAvailability) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(hotelAvailability);
        transaction.commit();
        session.close();
    }
}
