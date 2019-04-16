package com.itemsSharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "admin_projects")
public class AdminProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminProjectId;

    private int projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_projects_user_id")
    @JsonIgnore
    User admin;

    public AdminProjects() {
    }

    public int getAdminProjectId() {
        return adminProjectId;
    }

    public void setAdminProjectId(int adminProjectId) {
        this.adminProjectId = adminProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
