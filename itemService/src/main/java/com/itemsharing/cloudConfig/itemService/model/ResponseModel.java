package com.itemsharing.cloudConfig.itemService.model;

import com.itemsharing.cloudConfig.itemService.component.GetToday;

import java.util.Date;


public class ResponseModel {

    private String message;
    private  boolean status;
    private Date date = GetToday.getToday();

    public ResponseModel(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public ResponseModel() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
