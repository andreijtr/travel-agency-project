package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.AirportDTO;
import com.sda.travel_agency.logic.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> save(@RequestBody AirportDTO airportDTO) {
        airportService.save(airportDTO);
        return new ResponseEntity<String>("New airport has been added!", HttpStatus.OK);
    }
}
