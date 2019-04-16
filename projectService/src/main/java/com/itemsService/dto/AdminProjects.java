package com.itemsService.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


public class AdminProjects {

    private int adminProjectId;
    private int projectId;

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

}
