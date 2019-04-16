package com.itemsService.service.impl;

import com.amazonaws.services.apigateway.model.Op;
import com.itemsService.dto.*;
import com.itemsService.model.Project;
import com.itemsService.model.ProjectTasks;
import com.itemsService.model.ResponseModel;
import com.itemsService.model.TaskDev;
import com.itemsService.proxy.UserServiceProxy;
import com.itemsService.repository.ProjectRepository;
import com.itemsService.repository.ProjectTasksRepository;
import com.itemsService.repository.TaskDevRepository;
import com.itemsService.service.ProjectTasksService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectTasksServiceImpl implements ProjectTasksService {

    @Autowired
    ProjectTasksRepository projectTasksRepository;

    @Autowired
    TaskDevRepository taskDevRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserServiceProxy userServiceProxy;

    private static final Logger lOGGER = LoggerFactory.getLogger(ProjectTasksServiceImpl.class);

//    @LoadBalanced
//    @HystrixCommand(
//            fallbackMethod = "buildFallbackUser",
//            threadPoolKey = "taskByUserThreadPool",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "30"),
//                    @HystrixProperty(name = "maxQueueSize",value = "10")
//            }
//    )
    @Override
    public ResponseEntity<?> saveProjectTask(TaskDTO taskDTO) {

        Optional<Project> optionalProject = projectRepository.findById(taskDTO.getProjectId());
        if(!optionalProject.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Project id",false), HttpStatus.BAD_REQUEST);
        }
        ProjectTasks projectTasks = new ProjectTasks();
        projectTasks.setProject(optionalProject.get());
        projectTasks.setTaskName(taskDTO.getTaskName());
        projectTasks.setTaskStartTime(taskDTO.getTaskStartTime());
        projectTasks.setTaskEndTime(taskDTO.getTaskEndTime());
        projectTasks.setTaskDescription(taskDTO.getTaskDescription());

        try {
            projectTasks = projectTasksRepository.save(projectTasks);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        List<TaskDev> devList = new ArrayList<>();

        for(int dev : taskDTO.getDevList()){
            DisplayOneUserDTO displayOneUserDTO =  userServiceProxy.getOneUser(dev).getBody();
            if(displayOneUserDTO == null){
                lOGGER.info("invalid user id");
                return new ResponseEntity<>(new ResponseModel("Invalid User id",false), HttpStatus.BAD_REQUEST);
            } else {
                TaskDev taskDev = new TaskDev();
                taskDev.setDevId(dev);
                taskDev.setProjectTask(projectTasks);
                try {
                    taskDev = taskDevRepository.save(taskDev);
                    ProjectUserDTO projectUserDTO = new ProjectUserDTO();
                    projectUserDTO.setUserid(dev);
                    projectUserDTO.setProjectId(optionalProject.get().getProjectId());
                    userServiceProxy.saveDev(projectUserDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                devList.add(taskDev);
            }
        }
        projectTasks.setTaskDev(devList);
        try {
            projectTasks = projectTasksRepository.save(projectTasks);
            return new ResponseEntity<>(projectTasks,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @LoadBalanced
    @HystrixCommand(
            fallbackMethod = "buildFallbackDev",
            threadPoolKey = "taskByDevUserThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            }
    )
    @Override
    public ResponseEntity<?> saveTaskDev(TaskDevDTO taskDevDTO){
        Optional<ProjectTasks> optionalProjectTasks =  projectTasksRepository.findById(taskDevDTO.getTaskId());
        if(!optionalProjectTasks.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid task id",false),HttpStatus.OK);
        }

        List<TaskDev> devList = new ArrayList<>();

        for(int dev : taskDevDTO.getDevList()){
            DisplayOneUserDTO displayOneUserDTO =  userServiceProxy.getOneUser(dev).getBody();
            if(displayOneUserDTO == null){
                lOGGER.info("invalid user id");
                return new ResponseEntity<>(new ResponseModel("Invalid User id",false), HttpStatus.BAD_REQUEST);
            } else {
                TaskDev taskDev = new TaskDev();
                taskDev.setDevId(dev);
                taskDev.setProjectTask(optionalProjectTasks.get());
                try {
                    taskDev = taskDevRepository.save(taskDev);
                    ProjectUserDTO projectUserDTO = new ProjectUserDTO();
                    projectUserDTO.setUserid(dev);
                    projectUserDTO.setProjectId(optionalProjectTasks.get().getProject().getProjectId());
                    userServiceProxy.saveDev(projectUserDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                devList.add(taskDev);
            }
        }
        ProjectTasks projectTasks = optionalProjectTasks.get();
        projectTasks.setTaskDev(devList);
        try {
            projectTasksRepository.save(projectTasks);
            return new ResponseEntity<>(projectTasks,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public ResponseEntity<?> buildFallbackDev(TaskDevDTO taskDevDTO){
        ProjectTasks projectTasks = new ProjectTasks();
        lOGGER.info("fallback by circuit bracker");
        return new ResponseEntity(projectTasks, HttpStatus.OK);
    }

    public ResponseEntity<?> buildFallbackUser(TaskDTO taskDTO){
        ProjectTasks projectTasks = new ProjectTasks();
        lOGGER.info("fallback by circuit bracker");
        return new ResponseEntity(projectTasks, HttpStatus.OK);
    }
}
