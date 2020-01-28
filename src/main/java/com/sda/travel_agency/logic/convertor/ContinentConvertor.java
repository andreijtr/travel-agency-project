package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.logic.dto.ContinentDTO;
import com.sda.travel_agency.entities.Continent;
import org.springframework.stereotype.Component;

@Component
public class ContinentConvertor {

    public ContinentDTO convertContinentToContinentDTO(Continent continent) {
        return new ContinentDTO(continent.getName());
    }

    public Continent convertContinentDTOToContinent(ContinentDTO continentDTO) {
        return new Continent(continentDTO.getName());
    }
}
