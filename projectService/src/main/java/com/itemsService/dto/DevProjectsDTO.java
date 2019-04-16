package com.itemsService.dto;

import java.util.ArrayList;
import java.util.List;

public class DevProjectsDTO {

    List<Integer> devProjects = new ArrayList<>();

    public List<Integer> getDevProjects() {
        return devProjects;
    }

    public void setDevProjects(List<Integer> devProjects) {
        this.devProjects = devProjects;
    }
}
