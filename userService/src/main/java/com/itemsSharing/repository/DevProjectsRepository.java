package com.itemsSharing.repository;

import com.itemsSharing.model.AdminProjects;
import com.itemsSharing.model.DevProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DevProjectsRepository extends JpaRepository<DevProjects,Integer> {

    @Query("SELECT pd FROM DevProjects pd WHERE pd.dev.userId=?1 AND pd.projectId=?2")
    Optional<DevProjects> getDevProjects(int userId, int projectId);
}
