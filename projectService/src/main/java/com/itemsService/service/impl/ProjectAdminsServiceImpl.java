package com.itemsService.service.impl;

import com.itemsService.proxy.UserServiceProxy;
import com.itemsService.service.ProjectAdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProjectAdminsServiceImpl implements ProjectAdminsService {

    @Autowired
    UserServiceProxy userServiceProxy;

    public ResponseEntity<?> test() {
        return new ResponseEntity(userServiceProxy.getOneUser(1).getBody(), HttpStatus.OK);
    }
}
