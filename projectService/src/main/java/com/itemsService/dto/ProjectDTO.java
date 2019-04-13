package com.itemsService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ProjectDTO {

    @NotNull
    private String projectName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date projectStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date projectEndDate;

    @NotNull
    private String projectDescription;

    private List<TecnologiesDTO> tecnologiesDetails;

    private ClientDTO clientDetails;

    public ProjectDTO() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<TecnologiesDTO> getTecnologiesDetails() {
        return tecnologiesDetails;
    }

    public void setTecnologiesDetails(List<TecnologiesDTO> tecnologiesDetails) {
        this.tecnologiesDetails = tecnologiesDetails;
    }

    public ClientDTO getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDTO clientDetails) {
        this.clientDetails = clientDetails;
    }
}
