package com.itemsSharing.controller;

import com.itemsSharing.dto.LoginDTO;
import com.itemsSharing.dto.UserDTO;
import com.itemsSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.security.Principal;

@RestController
@RequestMapping("/staffUser")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public ResponseEntity testFirst() {
        String name = "I am lakith Muthugala";
        return new ResponseEntity(name, HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public ResponseEntity saveNewUser(
            @RequestParam MultipartFile profilePic,
            @RequestParam(required = true)  String name,
            @Valid @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email Must be in Correct Format")@RequestParam(required = true)  String email,
            @RequestParam(required = true)  String password,
            @RequestParam(required = true)  String userName,
            @RequestParam(required = true)  int roleId
    ) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setUsername(userName);
        userDTO.setProfilePic(profilePic);
        userDTO.setRoleId(roleId);

        return userService.saveNewUser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        return userService.userLogin(loginDTO);
    }

    @GetMapping(path = "/activate/{userId}")
    public ResponseEntity activateAuser(@PathVariable @Valid int userId){
        return userService.activateAUser(userId);
    }

    @GetMapping("/get-user-from-token")
    public ResponseEntity<?> getUserFromToken(Principal principal){
        return userService.getUserFromToken(principal);
    }

    @GetMapping("/get-one-user")
    public ResponseEntity<?> getOneUser(@RequestParam("user") int user){
        return userService.getOneUser(user);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }

}
