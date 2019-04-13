package com.itemsService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "project_admins")
public class ProjectAdmins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectAdminsId;

    @NotNull
    private int adminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_admins_project_id")
    @JsonIgnore
    Project project;


    public ProjectAdmins() {
    }

    public int getProjectAdminsId() {
        return projectAdminsId;
    }

    public void setProjectAdminsId(int projectAdminsId) {
        this.projectAdminsId = projectAdminsId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
