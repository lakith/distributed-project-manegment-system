package com.itemsService.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
