package com.itemsharing.cloudConfig.userService.Service.impl;

import com.itemsharing.cloudConfig.userService.dto.UserDisplayDTO;
import com.itemsharing.cloudConfig.userService.model.Role;
import com.itemsharing.cloudConfig.userService.CustomExeption.ResourceNotFoundException;
import com.itemsharing.cloudConfig.userService.Repository.RoleRepository;
import com.itemsharing.cloudConfig.userService.Repository.UserRepository;
import com.itemsharing.cloudConfig.userService.Repository.UserRoleRepository;
import com.itemsharing.cloudConfig.userService.Service.UserService;
import com.itemsharing.cloudConfig.userService.Utility.SecurityUtility;
import com.itemsharing.cloudConfig.userService.dto.UserDTO;
import com.itemsharing.cloudConfig.userService.model.ResponseModel;
import com.itemsharing.cloudConfig.userService.model.User;
import com.itemsharing.cloudConfig.userService.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger lOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public ResponseEntity<?> createUser(UserDTO userDTO) throws Exception {
        Optional<User> optionalUser =  userRepository.findByUsername(userDTO.getUsername());
        if(optionalUser.isPresent()){
            lOGGER.info("User Already exists",userDTO.getUsername());
            throw new Exception("User Already exists");
        } else {

            Optional<Role> optionalRole = roleRepository.findById(1L);
            if(!optionalRole.isPresent()){
                return new ResponseEntity<>(new ResponseModel("Invalid User Role Details",false),HttpStatus.BAD_REQUEST);
            }

            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setJoinDate(userDTO.getJoinDate());
            user.setUsername(userDTO.getUsername());
            String encryptedPassword = SecurityUtility.passwordEncoder().encode(userDTO.getPassword());
            user.setPassword(encryptedPassword);

            user = userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setRole(optionalRole.get());
            userRole.setUser(user);

            userRole = userRoleRepository.save(userRole);

            List<UserRole> userRoleList = new ArrayList<>();
            userRoleList.add(userRole);
            user.setUserRoleList(userRoleList);

            userRepository.save(user);

            Role role = optionalRole.get();
            List<UserRole> roleList = role.getUserRoleList();

            if(roleList.isEmpty()){
                roleList = new ArrayList<>();
            }

            roleList.add(userRole);

            roleRepository.save(role);

            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<User> getUserByUserName(String username) {
        Optional<User> optionalUser =  userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            lOGGER.info("Invalid User Details",username);
            throw new ResourceNotFoundException("Invalid User Details");
        } else {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
    }


    public ResponseEntity<UserDisplayDTO> getUserByUserNameForItemSerive(String username) {
        Optional<User> optionalUser =  userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            lOGGER.info("Invalid User Details.",username);
            throw new ResourceNotFoundException("Invalid User Details.");
        } else {

            UserDisplayDTO userDisplayDTO = new UserDisplayDTO();
            userDisplayDTO.setUserId(optionalUser.get().getUserId());
            userDisplayDTO.setFirstName(optionalUser.get().getFirstName());
            userDisplayDTO.setLastName(optionalUser.get().getLastName());
            userDisplayDTO.setEmail(optionalUser.get().getEmail());
            userDisplayDTO.setUsername(optionalUser.get().getUsername());
            userDisplayDTO.setEnabled(optionalUser.get().isEnabled());

            return new ResponseEntity<>(userDisplayDTO, HttpStatus.OK);
        }
    }
}
