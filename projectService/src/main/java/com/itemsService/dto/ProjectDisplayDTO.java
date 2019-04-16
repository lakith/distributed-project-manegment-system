package com.itemsService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itemsService.model.ProjectAdmins;
import com.itemsService.model.ProjectClient;
import com.itemsService.model.ProjectTecnologies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDisplayDTO {

    private int projectId;
    private String projectName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date projectStartDate;
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date projectEndDate;
    private String projectDescription;
    private List<ProjectAdmins> projectAdmins = new ArrayList<>();
    private List<TaskDisplayDTO> projectTasks = new ArrayList<>();
    private List<ProjectTecnologies> tecnologies = new ArrayList<>();
    private ProjectClient projectClient;

    public ProjectDisplayDTO() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<ProjectAdmins> getProjectAdmins() {
        return projectAdmins;
    }

    public void setProjectAdmins(List<ProjectAdmins> projectAdmins) {
        this.projectAdmins = projectAdmins;
    }

    public List<TaskDisplayDTO> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<TaskDisplayDTO> projectTasks) {
        this.projectTasks = projectTasks;
    }

    public List<ProjectTecnologies> getTecnologies() {
        return tecnologies;
    }

    public void setTecnologies(List<ProjectTecnologies> tecnologies) {
        this.tecnologies = tecnologies;
    }

    public ProjectClient getProjectClient() {
        return projectClient;
    }

    public void setProjectClient(ProjectClient projectClient) {
        this.projectClient = projectClient;
    }
}
