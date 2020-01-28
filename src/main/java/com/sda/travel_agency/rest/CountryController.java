package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.ContinentDTO;
import com.sda.travel_agency.logic.dto.CountryDTO;
import com.sda.travel_agency.logic.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> save(@RequestBody CountryDTO countryDTO) {
        countryService.save(countryDTO);
        return new ResponseEntity<String>("Country added!", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CountryDTO>> findByContinent(@RequestBody ContinentDTO continentDTO) {
        List<CountryDTO> countryDTOList = countryService.findByContinent(continentDTO);
        return new ResponseEntity<List<CountryDTO>>(countryDTOList, HttpStatus.OK);
    }
}
