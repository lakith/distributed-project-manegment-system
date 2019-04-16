package com.itemsSharing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ProjectTecnologies {

    private int tecnologyId;
    private String tecnologyType;
    private String tecnologyName;
    private Project project;

    public ProjectTecnologies() {
    }

    public int getTecnologyId() {
        return tecnologyId;
    }

    public void setTecnologyId(int tecnologyId) {
        this.tecnologyId = tecnologyId;
    }

    public String getTecnologyType() {
        return tecnologyType;
    }

    public void setTecnologyType(String tecnologyType) {
        this.tecnologyType = tecnologyType;
    }

    public String getTecnologyName() {
        return tecnologyName;
    }

    public void setTecnologyName(String tecnologyName) {
        this.tecnologyName = tecnologyName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
