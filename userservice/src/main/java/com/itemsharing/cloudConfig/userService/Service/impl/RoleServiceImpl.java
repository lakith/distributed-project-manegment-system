package com.itemsharing.cloudConfig.userService.Service.impl;

import com.itemsharing.cloudConfig.userService.Repository.RoleRepository;
import com.itemsharing.cloudConfig.userService.dto.RoleDTO;
import com.itemsharing.cloudConfig.userService.model.ResponseModel;
import com.itemsharing.cloudConfig.userService.model.Role;
import com.itemsharing.cloudConfig.userService.Service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger lOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<?> saveRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());

        Optional<Role> optionalRole = roleRepository.checkRole(roleDTO.getRoleName());
        if(optionalRole.isPresent()){
            lOGGER.info("User Role Already Exists",roleDTO);
            return new ResponseEntity<>(new ResponseModel("User Role Already Exists",false),HttpStatus.BAD_REQUEST);
        }
        role = roleRepository.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllUserRoles(){
        List<Role> roleList = roleRepository.findAll();
        if(roleList.isEmpty()){
            lOGGER.info("No Role Details Available");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(roleList,HttpStatus.OK);
        }
    }

}
