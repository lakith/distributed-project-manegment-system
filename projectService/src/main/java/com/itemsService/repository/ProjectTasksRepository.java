package com.itemsService.repository;

import com.itemsService.model.ProjectTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTasksRepository extends JpaRepository<ProjectTasks,Integer>{
}
