package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/export")
public class ExportController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(path = "/hotels")
    public ResponseEntity<String> export(@RequestBody Map<String, String> reqBody) {
        String path = reqBody.get("path");
        String message = hotelService.export(path);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
