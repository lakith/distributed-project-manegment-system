package com.itemsService.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TaskDevDTO {

    @NotNull
    private int taskId;

    private List<Integer> devList;

    public TaskDevDTO() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public List<Integer> getDevList() {
        return devList;
    }

    public void setDevList(List<Integer> devList) {
        this.devList = devList;
    }
}
