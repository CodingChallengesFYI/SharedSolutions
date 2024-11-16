package com.tinyurl.api.userservice.service;

import com.tinyurl.api.userservice.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserManagementService extends UserDetailsService {

    public UserDTO createUser(UserDTO userDto);
    public UserDTO getUserDetailsByEmail(String email);

}
