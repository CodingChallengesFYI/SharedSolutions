package com.tinyurl.api.userservice.service;

import com.tinyurl.api.userservice.model.UserDTO;
import com.tinyurl.api.userservice.model.UserEntity;
import com.tinyurl.api.userservice.repository.UserRepository;
import com.tinyurl.api.userservice.util.PasswordEncryptor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserManagmentServiceImpl implements UserManagementService{

    private static final Logger log = LoggerFactory.getLogger(UserManagmentServiceImpl.class);
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncryptor passwordEncryptor;

    @Autowired
    public UserManagmentServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncryptor = passwordEncryptor;
    }


    @Override
    public UserDTO createUser(UserDTO userDto) {
        try {
            log.info("Creating user: {}", userDto);
            // Encrypt password before saving to database
            userDto.setEncryptedPassword(passwordEncryptor.encryptPassword(userDto.getPassword()));
            userDto.setUserId(UUID.randomUUID().toString());

            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

            userRepository.save(userEntity);
        } catch (Exception ex) {
            log.error("Error creating user: {}", ex.getMessage(), ex);
            throw ex;
        }
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }

        return  modelMapper.map(userEntity, UserDTO.class);
    }
}
