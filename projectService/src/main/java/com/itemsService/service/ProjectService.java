package com.itemsService.service;

import com.itemsService.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProjectService {

    ResponseEntity<?> projectSave(ProjectDTO projectDTO, Principal principal);

}
