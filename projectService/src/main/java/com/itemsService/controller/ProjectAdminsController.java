package com.itemsService.controller;

import com.itemsService.dto.AdminDTO;
import com.itemsService.service.ProjectAdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@CrossOrigin
public class ProjectAdminsController {

    @Autowired
    ProjectAdminsService projectAdminsService;

    @PostMapping("/add-admins")
    public ResponseEntity<?> saveProjectAdmins(AdminDTO adminDTO){
        return projectAdminsService.saveProjectAdmins(adminDTO);
    }
}
