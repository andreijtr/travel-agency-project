package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.logic.dto.CityDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class CityDAO {

    public void save(City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(city);
        transaction.commit();
        session.close();
    }

    public City find(CityDTO cityDTO){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("city.findByNameAndCountry");
        query.setParameter("cityName", cityDTO.getName());
        query.setParameter("countryName", cityDTO.getCountryDTO().getName());
        City city = (City) query.getSingleResult();
        transaction.commit();
        session.close();
        return city;
    }
}
