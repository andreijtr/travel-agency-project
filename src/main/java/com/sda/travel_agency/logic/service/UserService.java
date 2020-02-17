package com.sda.travel_agency.logic.service;

import com.sda.travel_agency.entities.User;
import com.sda.travel_agency.logic.convertor.UserConvertor;
import com.sda.travel_agency.logic.dto.UserDTO;
import com.sda.travel_agency.repository.UserDAO;
import com.sda.travel_agency.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserConvertor userConvertor;

    public void save(UserDTO userDTO) {
        User user = userConvertor.convertToTransientUser(userDTO);
        userDAO.save(user);
    }

    public UserDTO find(String email) {
        try{
            User user = userDAO.find(email);
            return userConvertor.convertToDTO(user);
        } catch (NoResultException ex) {
            return null;
        }
    }

    public String login(UserDTO userDTO) {
        User userLogin = userConvertor.convertToTransientUser(userDTO);
        boolean login = userDAO.login(userLogin);
        return login ? Constants.USER_LOGIN_SUCCESSFULLY : Constants.USER_LOGIN_FAILED;
    }
}
