package com.sda.travel_agency.rest;

import com.sda.travel_agency.entities.Continent;
import com.sda.travel_agency.logic.dto.ContinentDTO;
import com.sda.travel_agency.logic.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> save(@RequestBody ContinentDTO continentDTO) {
        continentService.save(continentDTO);
        return new ResponseEntity<String>("Continent added!", HttpStatus.OK);
    }
}
