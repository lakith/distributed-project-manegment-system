package com.itemsSharing.service;

import com.itemsSharing.dto.LoginDTO;
import com.itemsSharing.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {

    ResponseEntity<?> saveNewUser(UserDTO userDTO) throws Exception;
    ResponseEntity<?> getUserFromToken(Principal principal);
    ResponseEntity<?> userLogin(LoginDTO loginDTO);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> getOneUser(int userID);
    ResponseEntity<?> activateAUser(int userId);
}
