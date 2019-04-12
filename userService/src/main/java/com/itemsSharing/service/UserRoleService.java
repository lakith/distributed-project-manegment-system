package com.itemsSharing.service;

import com.itemsSharing.model.UserRole;
import org.springframework.http.ResponseEntity;

public interface UserRoleService {

    ResponseEntity saveNewUserRole (UserRole userRole) throws Exception;
    ResponseEntity getAllUserRoles ();
}
