package com.itemsharing.cloudConfig.userService.Service;

import com.itemsharing.cloudConfig.userService.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createUser(UserDTO userDTO) throws Exception;

    ResponseEntity<?> getUserByUserName(String username);
}
