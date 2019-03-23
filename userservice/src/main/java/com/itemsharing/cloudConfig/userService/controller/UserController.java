package com.itemsharing.cloudConfig.userService.controller;

import com.itemsharing.cloudConfig.userService.Service.UserService;
import com.itemsharing.cloudConfig.userService.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        return userService.getUserByUserName(username);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createuser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.createUser(userDTO);
    }

}
