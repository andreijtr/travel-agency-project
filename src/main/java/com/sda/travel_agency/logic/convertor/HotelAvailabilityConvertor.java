package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.logic.dto.HotelAvailabilityDTO;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelAvailabilityConvertor {

    @Autowired
    private HotelConvertor hotelConvertor;

    @Autowired
    private HotelDAO hotelDAO;

    public HotelAvailabilityDTO convertHotelAvailabilityToDTO(HotelAvailability hotelAvailability) {
        HotelAvailabilityDTO hotelAvailabilityDTO = new HotelAvailabilityDTO();
        HotelDTO hotelDTO = hotelConvertor.convertHotelToHotelDTO(hotelAvailability.getHotel());

        hotelAvailabilityDTO.setHotelDTO(hotelDTO);
        hotelAvailabilityDTO.setStartDate(hotelAvailability.getStartDate());
        hotelAvailabilityDTO.setEndDate(hotelAvailability.getEndDate());
        hotelAvailabilityDTO.setSingleRooms(hotelAvailability.getSingleRooms());
        hotelAvailabilityDTO.setDoubleRooms(hotelAvailability.getDoubleRooms());
        hotelAvailabilityDTO.setExtraBeds(hotelAvailability.getExtraBeds());
        hotelAvailabilityDTO.setSingleRoomPrice(hotelAvailability.getSingleRoomPrice());
        hotelAvailabilityDTO.setDoubleRoomPrice(hotelAvailability.getDoubleRoomPrice());
        hotelAvailabilityDTO.setExtraBedPrice(hotelAvailability.getExtraBedPrice());

        return hotelAvailabilityDTO;
    }

    public HotelAvailability convertDTOToTransientObject(HotelAvailabilityDTO hotelAvailabilityDTO) {
        HotelAvailability hotelAvailability = new HotelAvailability();

        Hotel hotel = hotelDAO.find(hotelAvailabilityDTO.getHotelDTO());
        hotelAvailability.setHotel(hotel);
        hotelAvailability.setStartDate(hotelAvailabilityDTO.getStartDate());
        hotelAvailability.setEndDate(hotelAvailabilityDTO.getEndDate());
        hotelAvailability.setSingleRooms(hotelAvailabilityDTO.getSingleRooms());
        hotelAvailability.setDoubleRooms(hotelAvailabilityDTO.getDoubleRooms());
        hotelAvailability.setExtraBeds(hotelAvailabilityDTO.getExtraBeds());
        hotelAvailability.setSingleRoomPrice(hotelAvailabilityDTO.getSingleRoomPrice());
        hotelAvailability.setDoubleRoomPrice(hotelAvailabilityDTO.getDoubleRoomPrice());
        hotelAvailability.setExtraBedPrice(hotelAvailabilityDTO.getExtraBedPrice());

        return hotelAvailability;
    }
}
