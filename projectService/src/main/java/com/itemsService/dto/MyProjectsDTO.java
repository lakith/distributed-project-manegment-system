package com.itemsService.dto;

public class MyProjectsDTO {

    String name;
    String email;
    String userRole;
    String username;
    AdminProjectsDTO adminProjectsList;
    AdminDevProjects devAdminProjects;
    DevProjectsDTO devProjects;

    public MyProjectsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AdminProjectsDTO getAdminProjectsList() {
        return adminProjectsList;
    }

    public void setAdminProjectsList(AdminProjectsDTO adminProjectsList) {
        this.adminProjectsList = adminProjectsList;
    }

    public AdminDevProjects getDevAdminProjects() {
        return devAdminProjects;
    }

    public void setDevAdminProjects(AdminDevProjects devAdminProjects) {
        this.devAdminProjects = devAdminProjects;
    }

    public DevProjectsDTO getDevProjects() {
        return devProjects;
    }

    public void setDevProjects(DevProjectsDTO devProjects) {
        this.devProjects = devProjects;
    }
}
