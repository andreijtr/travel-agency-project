package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.logic.convertor.HotelConvertor;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private HotelConvertor hotelConvertor;

    //method to save a new hotel sent from frontend to DB
    public void save(HotelDTO hotelDTO) {
        Hotel hotel = hotelConvertor.convertToTransientHotel(hotelDTO);
        hotelDAO.save(hotel);
    }

    public List<HotelDTO> findAll() {
        List<Hotel> hotelList = hotelDAO.findAll();
        List<HotelDTO> hotelDTOList = hotelConvertor.convertToDTOList(hotelList);
        return hotelDTOList;
    }
}
