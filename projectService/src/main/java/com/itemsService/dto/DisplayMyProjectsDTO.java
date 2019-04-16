package com.itemsService.dto;

import java.util.List;

public class DisplayMyProjectsDTO {

    List<ProjectDisplayDTO> adminProjects;
    List<ProjectDisplayDTO> devProjects;
    List<ProjectDisplayDTO> devAdminProjects;

    public DisplayMyProjectsDTO() {
    }

    public List<ProjectDisplayDTO> getAdminProjects() {
        return adminProjects;
    }

    public void setAdminProjects(List<ProjectDisplayDTO> adminProjects) {
        this.adminProjects = adminProjects;
    }

    public List<ProjectDisplayDTO> getDevProjects() {
        return devProjects;
    }

    public void setDevProjects(List<ProjectDisplayDTO> devProjects) {
        this.devProjects = devProjects;
    }

    public List<ProjectDisplayDTO> getDevAdminProjects() {
        return devAdminProjects;
    }

    public void setDevAdminProjects(List<ProjectDisplayDTO> devAdminProjects) {
        this.devAdminProjects = devAdminProjects;
    }
}
