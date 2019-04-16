package com.itemsService.repository;

import com.itemsService.model.Project;
import com.itemsService.model.ProjectAdmins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectAdminsRepository extends JpaRepository<ProjectAdmins,Integer> {

    @Query("SELECT pa FROM ProjectAdmins pa WHERE pa.adminId=?1")
    List<Project> getAdminProjects(int email);

}
