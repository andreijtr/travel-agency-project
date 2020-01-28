package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.logic.dto.ContinentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ContinentDAO {

    public void save(Continent continent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(continent);
        transaction.commit();
        session.close();
    }

    public Continent findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("continent.findByName");
        query.setParameter("name", name);
        Continent continent = (Continent) query.getSingleResult();

        transaction.commit();
        session.close();
        return continent;
    }
}
