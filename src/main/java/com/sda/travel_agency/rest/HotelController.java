package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.logic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> save(@RequestBody HotelDTO hotelDTO) {
        hotelService.save(hotelDTO);
        return new ResponseEntity<String>("Hotel has been added to your travel agency!", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<HotelDTO>> findAll() {
        List<HotelDTO> hotelDTOList = hotelService.findAll();
        return new ResponseEntity<List<HotelDTO>>(hotelDTOList, HttpStatus.OK);
    }
}
