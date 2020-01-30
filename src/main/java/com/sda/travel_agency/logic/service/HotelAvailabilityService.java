package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.logic.convertor.HotelAvailabilityConvertor;
import com.sda.travel_agency.logic.dto.HotelAvailabilityDTO;
import com.sda.travel_agency.repository.HotelAvailabilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelAvailabilityService {

    @Autowired
    private HotelAvailabilityConvertor hotelAvailabilityConvertor;

    @Autowired
    private HotelAvailabilityDAO hotelAvailabilityDAO;

    public void save(HotelAvailabilityDTO hotelAvailabilityDTO) {
        HotelAvailability hotelAvailability = hotelAvailabilityConvertor.convertDTOToTransientObject(hotelAvailabilityDTO);
        hotelAvailabilityDAO.save(hotelAvailability);
    }
}
