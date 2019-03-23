package com.itemsharing.cloudConfig.userService.Repository;

import com.itemsharing.cloudConfig.userService.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Optional<Role> checkRole(String role);

}
