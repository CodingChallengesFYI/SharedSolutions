package com.tinyurl.api.userservice.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordEncryptor(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
