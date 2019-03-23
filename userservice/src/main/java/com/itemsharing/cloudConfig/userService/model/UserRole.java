package com.itemsharing.cloudConfig.userService.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userRoleid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_role_role")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userRole_user_id")
    private User user;

    public UserRole() {
    }

    public UserRole(Role role, User user) {
        this.role = role;
        this.user = user;
    }

    public long getUserRoleid() {
        return userRoleid;
    }

    public void setUserRoleid(long userRoleid) {
        this.userRoleid = userRoleid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
