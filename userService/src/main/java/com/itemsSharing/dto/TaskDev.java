package com.itemsSharing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class TaskDev {

    private int taskDevId;
    private int devId;

    public TaskDev() {
    }

    public int getTaskDevId() {
        return taskDevId;
    }

    public void setTaskDevId(int taskDevId) {
        this.taskDevId = taskDevId;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

}
