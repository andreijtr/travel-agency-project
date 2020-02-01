package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.FlightDTO;
import com.sda.travel_agency.logic.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> save(@RequestBody FlightDTO flightDTO){
        flightService.save(flightDTO);
        return new ResponseEntity<String>("New flight has been added to your travel agency!", HttpStatus.OK);
    }
}
