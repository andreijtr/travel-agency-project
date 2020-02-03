package com.sda.travel_agency.util;

import com.sda.travel_agency.entities.Flight;
import com.sda.travel_agency.logic.exception.NoSeatsAvailableException;

public class FlightUtil {

    public static void checkSeats (Flight flight, int seats) throws NoSeatsAvailableException {
        if (seats > flight.getAvailableSeats()) {
            throw new NoSeatsAvailableException(Consts.FLIGHT_NO_SEATS);
        }
    }
}
