package com.itemsSharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public class Project {

    private int projectId;
    private String projectName;
    private Date projectStartDate;
    private Date projectEndDate;
    private String projectDescription;
    private List<ProjectAdmins> projectAdmins;
    private List<ProjectTasks> projectTasks;
    private List<ProjectTecnologies> tecnologies;

    public Project() {
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

    public List<ProjectTasks> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<ProjectTasks> projectTasks) {
        this.projectTasks = projectTasks;
    }

    public List<ProjectTecnologies> getTecnologies() {
        return tecnologies;
    }

    public void setTecnologies(List<ProjectTecnologies> tecnologies) {
        this.tecnologies = tecnologies;
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
}
