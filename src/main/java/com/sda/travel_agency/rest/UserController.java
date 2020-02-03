package com.sda.travel_agency.rest;

import com.sda.travel_agency.logic.dto.UserDTO;
import com.sda.travel_agency.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return new ResponseEntity<>("New user has been added!", HttpStatus.OK);
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<UserDTO> find(@PathVariable String email) {
        UserDTO userDTO = userService.find(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String loginResponse = userService.login(userDTO);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
