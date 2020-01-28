package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.logic.convertor.CityConvertor;
import com.sda.travel_agency.logic.dto.CityDTO;
import com.sda.travel_agency.repository.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private CityConvertor cityConvertor;

    public void save(CityDTO cityDTO) {
        City city = cityConvertor.convertCityDTOToCity(cityDTO);
        cityDAO.save(city);
    }
}
