package com.itemsService.service;

import com.itemsService.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProjectService {

    ResponseEntity<?> projectSave(ProjectDTO projectDTO, Principal principal);

    ResponseEntity<?> getOneProject(int projectId);

    ResponseEntity<?> gellAllProjects();

    ResponseEntity<?> addprojectAdmins(int projectId,int userId);

    ResponseEntity<?> completedProjects();

    ResponseEntity<?> PendingProjects();
}
