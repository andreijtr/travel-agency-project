package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.TripDTO;
import com.sda.travel_agency.logic.dto.TripDetailDTO;
import com.sda.travel_agency.logic.exception.NoTripFoundException;
import com.sda.travel_agency.logic.service.TripDetailService;
import com.sda.travel_agency.logic.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripDetailService tripDetailService;

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
    public ResponseEntity<List<TripDTO>> find(@Valid @RequestBody TripDTO tripDTO) throws NoTripFoundException {
        List<TripDTO> tripDTOList = tripService.find(tripDTO);
        return new ResponseEntity<>(tripDTOList, HttpStatus.OK);
    }

    @PostMapping(path = "/buy")
    public ResponseEntity<String> buy(@RequestBody TripDetailDTO tripDetailDTO) {
        String result = tripDetailService.save(tripDetailDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
