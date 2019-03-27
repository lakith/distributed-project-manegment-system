package com.itemsharing.cloudConfig.itemService.controller;

import com.itemsharing.cloudConfig.itemService.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        return itemService.addItemByUser(null,username);
    }

    @GetMapping
    public String testApp(){
        return "success";
    }
}
