package com.itemsService.repository;

import com.itemsService.model.ProjectClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectClientsRepository extends JpaRepository<ProjectClient,Integer> {

       @Query("SELECT pc FROM  ProjectClient pc WHERE pc.project.projectId=?1")
       Optional<ProjectClient> getProjectClient (int projectId);

}
