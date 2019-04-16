package com.itemsService.service;

import com.itemsService.dto.AdminDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectAdminsService {
    ResponseEntity<?> test();
    ResponseEntity<?> saveProjectAdmins(AdminDTO adminDTO);
}
