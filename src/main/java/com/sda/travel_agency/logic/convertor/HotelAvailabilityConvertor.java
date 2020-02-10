package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.logic.dto.HotelAvailabilityDTO;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HotelAvailabilityConvertor {

    @Autowired
    private HotelConvertor hotelConvertor;

    @Autowired
    private HotelDAO hotelDAO;

    public HotelAvailabilityDTO convertHotelAvailabilityToDTO(HotelAvailability hotelAvailability) {
        HotelAvailabilityDTO hotelAvailabilityDTO = new HotelAvailabilityDTO();

        //aici nu mai convertesc si hotelul, pentru ca am stabilit ca o perioada sa fie convertita la DTO doar cand
        //se afiseaza alaturi de hotelul ei
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
        Hotel hotelTransient = hotelConvertor.convertToTransientHotel(hotelAvailabilityDTO.getHotelDTO());
        Hotel hotel = hotelDAO.find(hotelTransient);

        HotelAvailability hotelAvailability = new HotelAvailability();
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

    public Set<HotelAvailabilityDTO> convertToDTOSet(Set<HotelAvailability> hotelAvailabilitySet) {
        Set<HotelAvailabilityDTO> hotelAvailabilityDTOSet = new HashSet<>();

        for (HotelAvailability rooms : hotelAvailabilitySet) {
            HotelAvailabilityDTO roomsDTO = convertHotelAvailabilityToDTO(rooms);
            hotelAvailabilityDTOSet.add(roomsDTO);
        }
        return hotelAvailabilityDTOSet;
    }
}
