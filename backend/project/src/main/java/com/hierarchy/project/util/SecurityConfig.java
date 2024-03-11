package com.hierarchy.project.util;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig{
    private String passwordEncrypted;
    public String encryptData (String dataToEncrypt){
        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
        passwordEncrypted = criptografar.encode(dataToEncrypt);
        return passwordEncrypted;
    }

}
