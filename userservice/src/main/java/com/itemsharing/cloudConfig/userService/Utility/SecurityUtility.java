package com.itemsharing.cloudConfig.userService.Utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtility {

    private static final String SALT = "salt";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static  String randamPassword() {
        String SALTCHARS = "ASDFSDFSDFSDFDSFSFERXZCDSFSDF12344";
        StringBuilder salt = new StringBuilder();
        Random rand = new Random();

        while(salt.length() < 18 ){
            int index = (int) (rand.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        String saltStr = salt.toString();
        return saltStr;
    }

}
