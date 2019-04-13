package com.itemsService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "project_technologies")
public class ProjectTecnologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tecnologyId;

    @NotNull
    private String tecnologyType;

    @NotNull
    private String tecnologyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_technologies_project_id")
    @JsonIgnore
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
