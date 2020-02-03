package com.sda.travel_agency.util;

import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.logic.exception.NoAvailableRoomsException;

public class RoomsUtil {

    public static void checkAvailability (HotelAvailability hotelAvailability, int singleRooms, int doubleRooms, int extraBeds) throws NoAvailableRoomsException {
        if (hotelAvailability.getSingleRooms() >= singleRooms &&
                hotelAvailability.getDoubleRooms() >= doubleRooms &&
                hotelAvailability.getExtraBeds() >= extraBeds) {
        } else {
            throw new NoAvailableRoomsException(Consts.HOTEL_NO_ROOMS);
        }
    }

    public static double computePrice(HotelAvailability hotelAvailability, int singleRooms, int doubleRooms, int extraBeds) {
        double totalSinglePrice = hotelAvailability.getSingleRoomPrice() * singleRooms;
        double totalDoublePrice = hotelAvailability.getDoubleRoomPrice() * doubleRooms;
        double totalExtraBedsPrice = hotelAvailability.getExtraBedPrice() * extraBeds;
        double totalAmount = totalSinglePrice + totalDoublePrice + totalExtraBedsPrice;
        return totalAmount;
    }
}
