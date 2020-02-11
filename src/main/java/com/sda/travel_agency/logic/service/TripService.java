package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.City;
import com.sda.travel_agency.entities.Hotel;
import com.sda.travel_agency.entities.HotelAvailability;
import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.logic.convertor.HotelConvertor;
import com.sda.travel_agency.logic.convertor.TripConvertor;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.logic.dto.TripDTO;
import com.sda.travel_agency.logic.exception.NoTripFoundException;
import com.sda.travel_agency.repository.CityDAO;
import com.sda.travel_agency.repository.HotelAvailabilityDAO;
import com.sda.travel_agency.repository.HotelDAO;
import com.sda.travel_agency.repository.TripDAO;
import com.sda.travel_agency.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.ListIterator;

@Service
public class TripService {

    @Autowired
    private TripDAO tripDAO;

    @Autowired
    private HotelAvailabilityDAO hotelAvailabilityDAO;

    @Autowired
    private TripConvertor tripConvertor;

    @Autowired
    private HotelConvertor hotelConvertor;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private CityDAO cityDAO;

    public void save(TripDTO tripDTO) {
        Trip trip = tripConvertor.convertToTransientTrip(tripDTO);
        tripDAO.save(trip);
    }

    public List<TripDTO> findAll() {
        List<Trip> tripList = tripDAO.findAll();
        return tripConvertor.convertToDTOList(tripList);
    }

    public List<TripDTO> find(TripDTO tripDTO) throws NoTripFoundException {
        Trip trip = new Trip();
        trip.setCheckInDate(tripDTO.getCheckInDate());
        trip.setCheckOutDate(tripDTO.getCheckOutDate());
        City city = null;

        //next will try to set the hotel or the city// we are sure that city or hotel are set by user
        //check if hotel's name has been set by user
        HotelDTO hotelDTO = tripDTO.getHotelDTO();
        if (hotelDTO.getName() != null) {
            try {
                Hotel hotel = hotelDAO.find(hotelConvertor.convertToTransientHotel(tripDTO.getHotelDTO()));
                city = hotel.getCity();
                trip.setHotel(hotel);
            } catch (NoResultException ex) {
                System.out.println("No hotel found!");
                throw new NoTripFoundException("Sorry! The hotel with this name was not found!");
            }
        } else { //if not, search only by city
            try {
                city = cityDAO.find(tripDTO.getHotelDTO().getCityDTO());
            } catch (NoResultException ex) {
                System.out.println("No trips in this city!");
                throw new NoTripFoundException("Sorry! There is not trip in " + hotelDTO.getCityDTO().getName()
                        + " starting in " + trip.getCheckInDate() + " .");
            }
        }

        //find all trips by given parameters
        List<Trip> tripList = tripDAO.findByTripAndCity(trip, city);

        //for every trip found:
        //check hotel availability by number of persons, if not available remove it from list
        //search price for all type of rooms and set it to tripDTO
        tripList = checkHotelAvailability(tripList, tripDTO.getNumberOfPersons());
        if (tripList.isEmpty()) {
            System.out.println(Consts.INSUFFICIENT_PLACES_HOTEL);
            throw new NoTripFoundException(Consts.INSUFFICIENT_PLACES_HOTEL);
        }
        List<TripDTO> tripDTOList = tripConvertor.convertToDTOList(tripList);

        return tripDTOList;
    }

    public Trip findSingle(TripDTO tripDTO) {
        Trip trip = tripConvertor.convertToTransientTrip(tripDTO);
        return tripDAO.find(trip);
    }

    //am modificat ca un trip sa vina cu hotelul iar hotelul sa contina in setul de rooms, camerele pt perioada selectata
    //acum verific daca sunt locuri. am facut modificarea de mai sus pentru a putea afisa pretul camerelor in hotelDTO
    //am modificat si hotelDTO, hotelAvailabilityDTO
    private List<Trip> checkHotelAvailability(List<Trip> tripList, int numberOfPersons) {
        ListIterator<Trip> listIterator = tripList.listIterator();
        while(listIterator.hasNext()) {
            for (HotelAvailability rooms : listIterator.next().getHotel().getHotelAvailabilitySet()) {
                if (rooms.getSingleRooms() + 2 * rooms.getDoubleRooms() + rooms.getExtraBeds() < numberOfPersons){
                    listIterator.remove();
                }
            }
        }
        return tripList;
    }

    //ar trebui sa faci si pentru avion la fel
}
