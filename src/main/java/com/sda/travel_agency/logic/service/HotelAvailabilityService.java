package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.logic.convertor.HotelAvailabilityConvertor;
import com.sda.travel_agency.logic.dto.HotelAvailabilityDTO;
import com.sda.travel_agency.logic.exception.NoAvailableRoomsException;
import com.sda.travel_agency.repository.HotelAvailabilityDAO;
import com.sda.travel_agency.util.Constants;
import com.sda.travel_agency.util.RoomsUtil;
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

    // check for available rooms when try to BUY
    public void checkRooms(Trip trip, int singleRooms, int doubleRooms, int extraBeds) throws NoAvailableRoomsException {
        HotelAvailability rooms = hotelAvailabilityDAO.find(trip);
        if (rooms.getSingleRooms() >= singleRooms &&
                rooms.getDoubleRooms() >= doubleRooms &&
                rooms.getExtraBeds() >= extraBeds) {
            return;
        }
        throw new NoAvailableRoomsException(Constants.HOTEL_NO_ROOMS);
    }

    public double computeRoomsPrice(Trip trip, int singleRooms, int doubleRooms, int extraBeds) {
        HotelAvailability rooms = hotelAvailabilityDAO.find(trip);
        double totalSinglePrice = rooms.getSingleRoomPrice() * singleRooms;
        double totalDoublePrice = rooms.getDoubleRoomPrice() * doubleRooms;
        double totalExtraBedsPrice = rooms.getExtraBedPrice() * extraBeds;
        return totalSinglePrice + totalDoublePrice + totalExtraBedsPrice;
    }
}