package com.itemsSharing.controller;

import com.itemsSharing.dto.ProjectUserDTO;
import com.itemsSharing.service.UserProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user-project")
@CrossOrigin
public class UserProjects {

    @Autowired
    UserProjectsService userProjectsService;

    @PostMapping("/dev")
    public ResponseEntity saveDevProjects(@RequestBody @Valid ProjectUserDTO projectUserDTO){
        return userProjectsService.saveProjectDev(projectUserDTO);
    }

    @PostMapping("/admin")
    public ResponseEntity saveAdminProjects(@RequestBody @Valid ProjectUserDTO projectUserDTO){
        return userProjectsService.saveProjectAdmin(projectUserDTO);
    }

    @GetMapping("/my-projects")
    public ResponseEntity getMyProjects(@RequestParam("user-id") int userId){
        return userProjectsService.getAllMyProjects(userId);
    }

}
