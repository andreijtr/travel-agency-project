package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.CityDTO;
import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.logic.dto.TripDTO;
import com.sda.travel_agency.logic.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping(path = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody TripDTO tripDTO) {
        tripService.save(tripDTO);
        return new ResponseEntity<String>("New trip has been added in your travel agency!", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TripDTO>> findAll() {
        return new ResponseEntity<List<TripDTO>>(tripService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/find", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<TripDTO>> find(@RequestBody TripDTO tripDTO) {
        List<TripDTO> tripDTOList = tripService.find(tripDTO);
        return new ResponseEntity<>(tripDTOList, HttpStatus.OK);
    }
}
