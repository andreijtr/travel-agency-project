package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.HotelAvailabilityDTO;
import com.sda.travel_agency.logic.service.HotelAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hotel-rooms")
public class HotelAvailabilityController {

    @Autowired
    private HotelAvailabilityService hotelAvailabilityService;

    @PostMapping(path = "/add-period")
    public ResponseEntity<String> save(@RequestBody HotelAvailabilityDTO hotelAvailabilityDTO) {
        hotelAvailabilityService.save(hotelAvailabilityDTO);
        return new ResponseEntity<String>("New availability period has been added for hotel " + hotelAvailabilityDTO.getHotelDTO(), HttpStatus.OK);
    }
}
