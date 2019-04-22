package com.itemsService.repository;

import com.itemsService.model.Project;
import com.itemsService.model.ProjectClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Query("SELECT p FROM  Project p WHERE p.projectStatus=true")
    List<Project> getCompletedProjects();

    @Query("SELECT p FROM  Project p WHERE p.projectStatus=false")
    List<Project> getPendingProjects();

}
