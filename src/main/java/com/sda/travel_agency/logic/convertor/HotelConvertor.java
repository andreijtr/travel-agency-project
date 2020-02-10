package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.logic.dto.CityDTO;
import com.sda.travel_agency.logic.dto.HotelAvailabilityDTO;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.repository.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class HotelConvertor {

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private CityConvertor cityConvertor;

    @Autowired
    private HotelAvailabilityConvertor roomsConvertor;

    //create hotel in order to save it in DB
    public Hotel convertToTransientHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        City city = cityDAO.find(hotelDTO.getCityDTO());
        hotel.setCity(city);
        hotel.setName(hotelDTO.getName());
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setDescription(hotelDTO.getDescription());
        return hotel;
    }

    public HotelDTO convertToDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        CityDTO cityDTO = cityConvertor.convertCityToCityDTO(hotel.getCity());
        Set<HotelAvailabilityDTO> hotelAvailabilityDTOSet = roomsConvertor.convertToDTOSet(hotel.getHotelAvailabilitySet());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setStandard(hotel.getStandard());
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setCityDTO(cityDTO);
        hotelDTO.setHotelAvailabilityDTOSet(hotelAvailabilityDTOSet);
        return hotelDTO;
    }

    public List<HotelDTO> convertToDTOList(List<Hotel> hotelList) {
        List<HotelDTO> hotelDTOList = new LinkedList<>();
        for (Hotel hotel : hotelList) {
            HotelDTO hotelDTO = convertToDTO(hotel);
            hotelDTOList.add(hotelDTO);
        }
        return hotelDTOList;
    }
}
