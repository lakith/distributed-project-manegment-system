package com.itemsService.controller;

import com.itemsService.dto.TaskDTO;
import com.itemsService.dto.TaskDevDTO;
import com.itemsService.service.ProjectTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TasksController {

    @Autowired
    ProjectTasksService projectTasksService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> saveProjectTask(@RequestBody @Valid TaskDTO taskDTO){
        return projectTasksService.saveProjectTask(taskDTO);
    }

    @PostMapping("/add-dev")
    public ResponseEntity<?> addDev(@RequestBody @Valid TaskDevDTO taskDTO){
        return projectTasksService.saveTaskDev(taskDTO);
    }

}
