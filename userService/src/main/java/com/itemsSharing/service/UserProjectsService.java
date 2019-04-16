package com.itemsSharing.service;

import com.itemsSharing.dto.ProjectUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserProjectsService {

    ResponseEntity<?> saveProjectDev(ProjectUserDTO projectUserDTO);
    ResponseEntity<?> saveProjectAdmin(ProjectUserDTO projectUserDTO);
    ResponseEntity<?> getAllMyProjects(int userId);
}
