package com.itemsService.repository;

import com.itemsService.model.ProjectClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectClientsRepository extends JpaRepository<ProjectClient,Integer> {
}
