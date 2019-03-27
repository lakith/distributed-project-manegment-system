package com.itemsharing.cloudConfig.userService.Service;

import com.itemsharing.cloudConfig.userService.dto.UserDTO;
import com.itemsharing.cloudConfig.userService.dto.UserDisplayDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createUser(UserDTO userDTO) throws Exception;

    ResponseEntity<?> getUserByUserName(String username);

    ResponseEntity<UserDisplayDTO> getUserByUserNameForItemSerive(String username);
}
