package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.logic.dto.ContinentDTO;
import com.sda.travel_agency.logic.dto.CountryDTO;
import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CountryConvertor {

    @Autowired
    private ContinentConvertor continentConvertor;

    public Country convertCountryDTOToCountry(CountryDTO countryDTO) {
        Country country = new Country();
        Continent continent = continentConvertor.convertContinentDTOToContinent(countryDTO.getContinentDTO());
        country.setName(countryDTO.getName());
        country.setContinent(continent);
        return country;
    }

    public CountryDTO convertCountryToCountryDTO(Country country){
        CountryDTO countryDTO = new CountryDTO();
        ContinentDTO continentDTO = continentConvertor.convertContinentToContinentDTO(country.getContinent());
        countryDTO.setName(country.getName());
        countryDTO.setContinentDTO(continentDTO);
        return countryDTO;
    }

    public List<CountryDTO> convertCountryListToCountryDTOList(List<Country> countryList) {
        List<CountryDTO> countryDTOList = new LinkedList<>();
        for (Country country : countryList) {
            CountryDTO countryDTO = convertCountryToCountryDTO(country);
            countryDTOList.add(countryDTO);
        }
        return countryDTOList;
    }
}
