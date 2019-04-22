package com.itemsService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "project_client")
public class ProjectClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    private String clientName;

    private String clientEmail;

    private String clientMobile;

    @Lob
    @Column( length = 100000)
    private String clientWebUri;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_client_project_id")
    @JsonIgnore
    private Project project;



    public ProjectClient() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public String getClientWebUri() {
        return clientWebUri;
    }

    public void setClientWebUri(String clientWebUri) {
        this.clientWebUri = clientWebUri;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
