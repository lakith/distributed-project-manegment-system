package com.itemsSharing.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;

    @NotNull
    private String userRoleType;

    public UserRole() {
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }
}
