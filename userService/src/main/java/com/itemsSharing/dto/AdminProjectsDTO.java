package com.itemsSharing.dto;

import java.util.ArrayList;
import java.util.List;

public class AdminProjectsDTO {
    List<Integer> adminProjects = new ArrayList<>();

    public List<Integer> getAdminProjects() {
        return adminProjects;
    }

    public void setAdminProjects(List<Integer> adminProjects) {
        this.adminProjects = adminProjects;
    }
}
