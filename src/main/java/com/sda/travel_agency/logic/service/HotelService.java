package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.logic.convertor.HotelConvertor;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.logic.export.ExcelExportHotel;
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

    @Autowired
    private ExcelExportHotel exportHotel;

    //method to save a new hotel sent from frontend to DB
    public void save(HotelDTO hotelDTO) {
        Hotel hotel = hotelConvertor.convertToTransientHotel(hotelDTO);
        hotelDAO.save(hotel);
    }

    public List<HotelDTO> findAll() {
        List<Hotel> hotelList = hotelDAO.findAll();
        return hotelConvertor.convertToDTOList(hotelList);
    }

    public String export(String path) {
        List<HotelDTO> hotelDTOList = findAll();
        return exportHotel.export(hotelDTOList, path);
    }

}
