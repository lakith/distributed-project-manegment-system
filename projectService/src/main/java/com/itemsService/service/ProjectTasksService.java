package com.itemsService.service;

import com.itemsService.dto.TaskDTO;
import com.itemsService.dto.TaskDevDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectTasksService {
    ResponseEntity<?> saveTaskDev(TaskDevDTO taskDevDTO);
    ResponseEntity<?> saveProjectTask(TaskDTO taskDTO);
}
