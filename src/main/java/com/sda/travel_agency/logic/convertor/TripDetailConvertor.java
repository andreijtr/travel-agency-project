package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.Trip;
import com.sda.travel_agency.entities.TripDetail;
import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.logic.dto.TripDTO;
import com.sda.travel_agency.logic.dto.TripDetailDTO;
import com.sda.travel_agency.logic.dto.UserDTO;
import com.sda.travel_agency.repository.TripDAO;
import com.sda.travel_agency.repository.UserDAO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Component
public class TripDetailConvertor {

    @Autowired
    private TripConvertor tripConvertor;
    @Autowired
    private UserConvertor userConvertor;
    @Autowired
    private TripDAO tripDAO;
    @Autowired
    private UserDAO userDAO;

    public TripDetailDTO convertToDTO(TripDetail tripDetail) {
        TripDTO tripDTO = tripConvertor.convertToDTO(tripDetail.getTrip());
        UserDTO userDTO = userConvertor.convertToDTO(tripDetail.getUser());

        TripDetailDTO tripDetailDTO = new TripDetailDTO();
        tripDetailDTO.setTripDTO(tripDTO);
        tripDetailDTO.setUserDTO(userDTO);
        tripDetailDTO.setDateOfPurchase(tripDetail.getDateOfPurchase());
        tripDetailDTO.setTripNumber(tripDetail.getTripNumber());
        tripDetailDTO.setSingleRooms(tripDetail.getSingleRooms());
        tripDetailDTO.setDoubleRooms(tripDetail.getDoubleRooms());
        tripDetailDTO.setExtraBeds(tripDetail.getExtraBeds());
        tripDetail.setAmount(tripDetail.getAmount());

        return tripDetailDTO;
    }

    public TripDetail convertToTransientTripDetail(TripDetailDTO tripDetailDTO) {
        Trip trip = tripDAO.find(tripConvertor.convertToTransientTrip(tripDetailDTO.getTripDTO()));
        User user = userDAO.find(tripDetailDTO.getUserDTO().getEmail());

        Calendar calendar = Calendar.getInstance();
        java.util.Date dateUtil = calendar.getTime();
        java.sql.Date dateOfPurchase = new Date(dateUtil.getTime());
        TripDetail tripDetail = new TripDetail();
        tripDetail.setTrip(trip);
        tripDetail.setUser(user);
        tripDetail.setSingleRooms(tripDetailDTO.getSingleRooms());
        tripDetail.setDoubleRooms(tripDetailDTO.getDoubleRooms());
        tripDetail.setExtraBeds(tripDetailDTO.getExtraBeds());
        tripDetail.setDateOfPurchase(dateOfPurchase);
        tripDetail.setTripNumber(UUID.randomUUID().toString());
        tripDetail.setAmount(tripDetailDTO.getAmount());

        return tripDetail;
    }

}
