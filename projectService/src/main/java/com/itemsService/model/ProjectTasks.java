package com.itemsService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project_tasks")
public class ProjectTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectTaskId;

    @NotNull
    private String taskName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date taskStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date taskEndTime;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_task_task_dev_id")
    private List<TaskDev> taskDev;

    @Lob
    @Column( length = 100000)
    private String taskDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_task_project_id")
    @JsonIgnore
    private Project project;

    private boolean taskStatus = false;

    public ProjectTasks() {
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getProjectTaskId() {
        return projectTaskId;
    }

    public void setProjectTaskId(int projectTaskId) {
        this.projectTaskId = projectTaskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public List<TaskDev> getTaskDev() {
        return taskDev;
    }

    public void setTaskDev(List<TaskDev> taskDev) {
        this.taskDev = taskDev;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
