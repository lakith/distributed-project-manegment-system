package com.itemsService.controller;

import com.itemsService.dto.ProjectDTO;
import com.itemsService.service.ProjectAdminsService;
import com.itemsService.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/proj")
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectAdminsService projectAdminsService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/test")
    public ResponseEntity<?> testController(){
        return new ResponseEntity<>(projectAdminsService.test(), HttpStatus.OK);
    }

    @PostMapping("/project-save")
    public ResponseEntity<?> projectSave(@RequestBody @Valid ProjectDTO projectDTO, Principal principal){
        return projectService.projectSave(projectDTO,principal);
    }

    @GetMapping("/one-Project")
    public ResponseEntity<?> getProject(@RequestParam("project_id") int projectId){
        return projectService.getOneProject(projectId);
    }

    @GetMapping("/all-projects")
    public ResponseEntity<?> getAllProjects(){
        return projectService.gellAllProjects();
    }
}
