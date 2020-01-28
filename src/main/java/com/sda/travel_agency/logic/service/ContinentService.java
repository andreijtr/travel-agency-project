package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.config.HibernateUtil;
import com.sda.travel_agency.logic.dto.ContinentDTO;
import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.logic.convertor.ContinentConvertor;
import com.sda.travel_agency.repository.ContinentDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;

@Service
public class ContinentService {

    @Autowired
    private ContinentDAO continentDAO;

    @Autowired
    private ContinentConvertor continentConvertor;

    public void save(ContinentDTO continentDTO) {
        Continent continent = continentConvertor.convertContinentDTOToContinent(continentDTO);
        continentDAO.save(continent);
    }

    public Continent find(ContinentDTO continentDTO) {
        return continentDAO.findByName(continentDTO.getName());
    }
}
