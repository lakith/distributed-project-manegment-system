package com.itemsService.service;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProjectUserService {
    ResponseEntity<?> projectUser(Principal principal);
}
