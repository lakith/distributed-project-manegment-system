package com.itemsSharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "dev_admin_projects")
public class DevAdminProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int devAdminProjectId;

    private int projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_admin_projects_user_id")
    @JsonIgnore
    User devAdmin;

    public DevAdminProjects() {
    }

    public int getDevAdminProjectId() {
        return devAdminProjectId;
    }

    public void setDevAdminProjectId(int devAdminProjectId) {
        this.devAdminProjectId = devAdminProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public User getDevAdmin() {
        return devAdmin;
    }

    public void setDevAdmin(User devAdmin) {
        this.devAdmin = devAdmin;
    }
}
