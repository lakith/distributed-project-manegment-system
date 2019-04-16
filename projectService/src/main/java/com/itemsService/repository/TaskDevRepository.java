package com.itemsService.repository;

import com.itemsService.model.Project;
import com.itemsService.model.TaskDev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskDevRepository extends JpaRepository<TaskDev,Integer> {

//    @Query("SELECT pa FROM  pa WHERE pa.adminId=?1")
//    List<Project> getAdminProjects(int email);

}
