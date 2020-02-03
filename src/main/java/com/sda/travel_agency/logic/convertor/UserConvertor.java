package com.sda.travel_agency.logic.convertor;

import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.logic.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    @Autowired
    private HashString hashString;

    public UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setAmount(user.getTotalAmount());
        return userDTO;
    }

    public User convertToTransientUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashString.getPasswordSHA(userDTO.getPassword()));
        user.setTotalAmount(userDTO.getAmount());
        return user;
    }
}
