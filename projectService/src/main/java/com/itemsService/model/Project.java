package com.itemsService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @NotNull
    private String projectName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date projectStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date projectEndDate;

    @Lob
    @Column( length = 100000)
    private String projectDescription;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_admins_project_id")
    private List<ProjectAdmins> projectAdmins;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_task_project_id")
    private List<ProjectTasks> projectTasks;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_technologies_project_id")
    private List<ProjectTecnologies> tecnologies;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_project_client_id")
    @JsonIgnore
    private ProjectClient projectClient;

    private boolean projectStatus = false;

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

    public ProjectClient getProjectClient() {
        return projectClient;
    }

    public void setProjectClient(ProjectClient projectClient) {
        this.projectClient = projectClient;
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

    public boolean isProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(boolean projectStatus) {
        this.projectStatus = projectStatus;
    }
}
