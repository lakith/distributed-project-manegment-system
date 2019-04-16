package com.itemsService.controller;

import com.itemsService.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/project-user")
@CrossOrigin
public class ProjectUserController {

    @Autowired
    ProjectUserService projectUserService;

    @GetMapping
    public ResponseEntity<?> getMyProjects(Principal principal){
        return projectUserService.projectUser(principal);
    }
}
