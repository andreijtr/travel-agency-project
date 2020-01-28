package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.entities.Country;
import com.sda.travel_agency.logic.dto.CountryDTO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CountryDAO {

    public void save(Country country) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(country);
        transaction.commit();
        session.close();
    }

    public List<Country> findByContinent(Continent continent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("country.findByContinent");
        query.setParameter("continent", continent);
        List<Country> countryList = query.getResultList();
        transaction.commit();
        session.close();
        return countryList;
    }

    public Country findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("country.findByName");
        query.setParameter("name", name);
        Country countryResult = (Country) query.getSingleResult();
        transaction.commit();
        session.close();
        return countryResult;
    }
}
