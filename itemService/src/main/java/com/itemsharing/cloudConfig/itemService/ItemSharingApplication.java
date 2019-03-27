package com.itemsharing.cloudConfig.itemService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients("com.itemsharing.cloudConfig.itemService")
public class ItemSharingApplication {
    public static void main(String[] args){
        SpringApplication.run(ItemSharingApplication.class,args);
    }
}
