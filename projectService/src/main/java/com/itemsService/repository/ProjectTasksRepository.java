package com.itemsService.repository;

import com.itemsService.model.ProjectTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectTasksRepository extends JpaRepository<ProjectTasks,Integer>{

    @Query("SELECT pt FROM ProjectTasks pt WHERE pt.project.projectId=?1")
    List<ProjectTasks> getUserTasks(int projectId);

}
