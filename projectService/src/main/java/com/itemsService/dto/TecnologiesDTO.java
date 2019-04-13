package com.itemsService.dto;

import javax.validation.constraints.NotNull;

public class TecnologiesDTO {

    @NotNull
    private String tecnologyType;

    @NotNull
    private String tecnologyName;

    public TecnologiesDTO() {
    }

    public String getTecnologyType() {
        return tecnologyType;
    }

    public void setTecnologyType(String tecnologyType) {
        this.tecnologyType = tecnologyType;
    }

    public String getTecnologyName() {
        return tecnologyName;
    }

    public void setTecnologyName(String tecnologyName) {
        this.tecnologyName = tecnologyName;
    }
}
