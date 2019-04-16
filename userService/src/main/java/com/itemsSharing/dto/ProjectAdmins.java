package com.itemsSharing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ProjectAdmins {

    private int projectAdminsId;
    private int adminId;

    public ProjectAdmins() {
    }

    public int getProjectAdminsId() {
        return projectAdminsId;
    }

    public void setProjectAdminsId(int projectAdminsId) {
        this.projectAdminsId = projectAdminsId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

}
