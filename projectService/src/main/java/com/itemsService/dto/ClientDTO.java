package com.itemsService.dto;

import javax.validation.constraints.NotNull;

public class ClientDTO {

    @NotNull
    private String clientName;

    @NotNull
    private String clientEmail;

    @NotNull
    private String clientMobile;

    @NotNull
    private String clientWebUri;

    public ClientDTO() {
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
}
