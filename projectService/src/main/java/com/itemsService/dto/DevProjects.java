package com.itemsService.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class DevProjects {

    private int devProjectId;
    private int projectId;

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

}
