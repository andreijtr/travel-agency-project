package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.logic.convertor.ContinentConvertor;
import com.sda.travel_agency.logic.dto.ContinentDTO;
import com.sda.travel_agency.logic.dto.CountryDTO;
import com.sda.travel_agency.entities.Country;
import com.sda.travel_agency.logic.convertor.CountryConvertor;
import com.sda.travel_agency.repository.ContinentDAO;
import com.sda.travel_agency.repository.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private ContinentDAO continentDAO;

    @Autowired
    private CountryConvertor countryConvertor;

    @Autowired
    private ContinentConvertor continentConvertor;

    public void save(CountryDTO countryDTO) {
        Country country = countryConvertor.convertCountryDTOToCountry(countryDTO);
        Continent continent = continentDAO.findByName(countryDTO.getContinentDTO().getName());
        country.setContinent(continent);
        countryDAO.save(country);
    }

    public List<CountryDTO> findByContinent(ContinentDTO continentDTO) {
        Continent continent = continentDAO.findByName(continentDTO.getName());
        List<Country> countryList = countryDAO.findByContinent(continent);
        return countryConvertor.convertCountryListToCountryDTOList(countryList);
    }
}
