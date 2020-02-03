package com.sda.travel_agency.repository;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.util.Consts;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserDAO {

    public void save(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();
        session.close();
    }

    public User find(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("user.find");
        query.setParameter("email", email);
        User userFound = (User) query.getSingleResult();
        transaction.commit();
        session.close();
        return userFound;
    }

    public boolean login(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNamedQuery("user.login");
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        try{
            query.getSingleResult();
            transaction.commit();
            session.close();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }
}
