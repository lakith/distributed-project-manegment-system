package com.itemsService.dto;

import java.util.List;

public class AdminDTO {

    private int projectId;
    private List<Integer> adminIds;

    public AdminDTO() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Integer> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(List<Integer> adminIds) {
        this.adminIds = adminIds;
    }
}
