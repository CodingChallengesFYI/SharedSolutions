package com.tinyurl.api.userservice.controller;

import com.tinyurl.api.userservice.model.LoginRequestModel;
import com.tinyurl.api.userservice.model.UserDTO;
import com.tinyurl.api.userservice.model.UserRequestModel;
import com.tinyurl.api.userservice.model.UserResponseModel;
import com.tinyurl.api.userservice.service.UserManagementService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final ModelMapper modelMapper;
    private final UserManagementService userManagementService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserManagementService userManagementService) {
        this.modelMapper = modelMapper;
        this.userManagementService = userManagementService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) {
        UserDTO userDTO = modelMapper.map(userRequestModel, UserDTO.class);
        UserResponseModel responseBody = modelMapper.map(userManagementService.createUser(userDTO), UserResponseModel.class);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    /*@PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginRequestModel loginRequestModel) {
        UserDTO userDTO = modelMapper.map(loginRequestModel, UserDTO.class);

        return "login user";
    }*/
}
