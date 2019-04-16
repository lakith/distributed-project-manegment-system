package com.itemsService.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


public class DevAdminProjects {

    private int devAdminProjectId;
    private int projectId;

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

}
