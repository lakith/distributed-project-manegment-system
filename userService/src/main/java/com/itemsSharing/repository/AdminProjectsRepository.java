package com.itemsSharing.repository;

import com.itemsSharing.model.AdminProjects;
import com.itemsSharing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminProjectsRepository extends JpaRepository<AdminProjects,Integer>{

    @Query("SELECT pa FROM AdminProjects pa WHERE pa.admin.userId=?1 AND pa.projectId=?2")
    Optional<AdminProjects> getAdminProjects(int userId,int projectId);

}
