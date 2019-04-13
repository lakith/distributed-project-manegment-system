package com.itemsService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task_dev")
public class TaskDev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskDevId;

    @NotNull
    private int devId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_task_task_dev_id")
    @JsonIgnore
    ProjectTasks projectTask;

    public TaskDev() {
    }

    public int getTaskDevId() {
        return taskDevId;
    }

    public void setTaskDevId(int taskDevId) {
        this.taskDevId = taskDevId;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public ProjectTasks getProjectTask() {
        return projectTask;
    }

    public void setProjectTask(ProjectTasks projectTask) {
        this.projectTask = projectTask;
    }
}
