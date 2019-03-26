package com.itemsharing.cloudConfig.itemService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemSharingApplication {

    public static void main(String[] args){
        SpringApplication.run(ItemSharingApplication.class,args);
    }
}
