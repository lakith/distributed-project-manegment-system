package com.itemsService.repository;

import com.itemsService.model.TaskDev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDevRepository extends JpaRepository<TaskDev,Integer> {
}
