package com.itemsSharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "dev_projects")
public class DevProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int devProjectId;

    private int projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_projects_user_id")
    @JsonIgnore
    User dev;

    public DevProjects() {
    }

    public int getDevProjectId() {
        return devProjectId;
    }

    public void setDevProjectId(int devProjectId) {
        this.devProjectId = devProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public User getDev() {
        return dev;
    }

    public void setDev(User dev) {
        this.dev = dev;
    }
}
