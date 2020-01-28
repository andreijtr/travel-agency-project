package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.CityDTO;
import com.sda.travel_agency.logic.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> save(@RequestBody CityDTO cityDTO) {
        cityService.save(cityDTO);
        return new ResponseEntity<String>("City added!", HttpStatus.OK);
    }
}
