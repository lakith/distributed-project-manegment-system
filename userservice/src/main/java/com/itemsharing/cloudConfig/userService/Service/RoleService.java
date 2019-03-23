package com.itemsharing.cloudConfig.userService.Service;

import com.itemsharing.cloudConfig.userService.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<?> saveRole(RoleDTO roleDTO);

    ResponseEntity<?> getAllUserRoles();

}
