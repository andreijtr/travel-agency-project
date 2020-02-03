package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.entities.TripDetail;
import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.logic.convertor.TripDetailConvertor;
import com.sda.travel_agency.logic.dto.TripDetailDTO;
import com.sda.travel_agency.repository.TripDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripDetailService {

    @Autowired
    private TripDetailConvertor tripDetailConvertor;

    @Autowired
    private TripDetailDAO tripDetailDAO;

    public String save(TripDetailDTO tripDetailDTO) {
        int numberOfPersons = tripDetailDTO.getPersons();
        TripDetail tripDetail = tripDetailConvertor.convertToTransientTripDetail(tripDetailDTO);
        return tripDetailDAO.save(tripDetail, numberOfPersons);
    }
}
