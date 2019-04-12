package com.itemsSharing.service.impl;

import com.itemsSharing.model.ResponseModel;
import com.itemsSharing.model.UserRole;
import com.itemsSharing.repository.UserRoleRepository;
import com.itemsSharing.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public ResponseEntity saveNewUserRole (UserRole userRole) throws Exception {
        try{
            userRoleRepository.save(userRole);
            userRole.setUserRoleType(userRole.getUserRoleType().toUpperCase());
            return new ResponseEntity(new ResponseModel("User Role Added Successfully",true), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity getAllUserRoles () {
        try{
            List<UserRole> userRoles = userRoleRepository.findAll();
            return new ResponseEntity(userRoles,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(new ResponseModel("Something went wrong", false),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}