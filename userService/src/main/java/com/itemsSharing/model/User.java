package com.itemsSharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email Must be in Correct Format")
    @Column(unique = true)
    private String email;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    @JsonIgnore
    private String password;

    private boolean active = true;

    @Lob
    @Column( length = 100000)
    private String profilePic;

    private String refeshToken;

    @NonNull
    @OneToOne
    @JoinColumn(name = "roleId")
    private UserRole userRole;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_projects_user_id")
    List<DevProjects> devProjects;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_projects_user_id")
    List<AdminProjects> adminProjects;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_admin_projects_user_id")
    List<DevAdminProjects> devAdminProjects;

    public User() {
    }

    public List<DevProjects> getDevProjects() {
        return devProjects;
    }

    public void setDevProjects(List<DevProjects> devProjects) {
        this.devProjects = devProjects;
    }

    public List<AdminProjects> getAdminProjects() {
        return adminProjects;
    }

    public void setAdminProjects(List<AdminProjects> adminProjects) {
        this.adminProjects = adminProjects;
    }

    public List<DevAdminProjects> getDevAdminProjects() {
        return devAdminProjects;
    }

    public void setDevAdminProjects(List<DevAdminProjects> devAdminProjects) {
        this.devAdminProjects = devAdminProjects;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getRefeshToken() {
        return refeshToken;
    }

    public void setRefeshToken(String refeshToken) {
        this.refeshToken = refeshToken;
    }

    @NonNull
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(@NonNull UserRole userRole) {
        this.userRole = userRole;
    }
}
