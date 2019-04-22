package com.itemsService.service.impl;

import com.itemsService.dto.*;
import com.itemsService.model.*;
import com.itemsService.proxy.UserServiceProxy;
import com.itemsService.repository.ProjectClientsRepository;
import com.itemsService.repository.ProjectRepository;
import com.itemsService.repository.ProjectTasksRepository;
import com.itemsService.service.ProjectUserService;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserServiceProxy userServiceProxy;

    @Autowired
    ProjectTasksRepository projectTasksRepository;

    @Autowired
    ProjectClientsRepository projectClientsRepository;

    private static final Logger lOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @SuppressWarnings("Duplicates")
    @Override
    @LoadBalanced
//    @HystrixCommand(
//            fallbackMethod = "buildFallbackUser",
//            threadPoolKey = "myProjectsThreadPool",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "30"),
//                    @HystrixProperty(name = "maxQueueSize",value = "10")
//            }
//    )
    public ResponseEntity<?> projectUser(Principal principal) {
        int userId = Integer.parseInt(principal.getName());
        MyProjectsDTO myProjectsDTO = userServiceProxy.getMyProjects(userId).getBody();
        DisplayOneUserDTO displayOneUserDTO =  userServiceProxy.getOneUser(userId).getBody();
        if(displayOneUserDTO == null){
            lOGGER.info("invalid user id");
            return new ResponseEntity<>(new ResponseModel("Invalid User Id",false),HttpStatus.BAD_REQUEST);
        }
        DisplayMyProjectsDTO displayMyProjectsDTO = new DisplayMyProjectsDTO();
        List<ProjectDisplayDTO> adminProjects = new ArrayList<>();
        List<ProjectDisplayDTO> devProjects = new ArrayList<>();
        List<ProjectDisplayDTO> devAdminProjects = new ArrayList<>();
        if(myProjectsDTO != null) {
            for (int project : myProjectsDTO.getAdminProjectsList().getAdminProjects()) {
                Optional<Project> optionalProject = projectRepository.findById(project);
                if (!optionalProject.isPresent()) {
                    continue;
                } else {
                    ProjectDisplayDTO projectDisplayDTO = new ProjectDisplayDTO();

                    projectDisplayDTO.setProjectId(optionalProject.get().getProjectId());
                    projectDisplayDTO.setProjectName(optionalProject.get().getProjectName());
                    projectDisplayDTO.setProjectStartDate(optionalProject.get().getProjectStartDate());
                    projectDisplayDTO.setProjectEndDate(optionalProject.get().getProjectEndDate());
                    projectDisplayDTO.setProjectDescription(optionalProject.get().getProjectDescription());


                    List<DisplayOneUserDTO> displayOneUserDTOList = new ArrayList<>();
                    for(ProjectAdmins projectAdmins : optionalProject.get().getProjectAdmins()){
                        DisplayOneUserDTO displayOneUserDTOAdmin = userServiceProxy.getOneUser(projectAdmins.getAdminId()).getBody();
                        if(displayMyProjectsDTO != null) {
                           displayOneUserDTOList.add(displayOneUserDTOAdmin);
                        }
                    }
                    projectDisplayDTO.setProjectAdmins(displayOneUserDTOList);

                    Optional<ProjectClient> optionalProjectClient= projectClientsRepository.getProjectClient(project);
                    projectDisplayDTO.setProjectClient(optionalProjectClient.get());

                    adminProjects.add(projectDisplayDTO);
                }
            }

            for (int project : myProjectsDTO.getDevProjects().getDevProjects()) {
                Optional<Project> optionalProject = projectRepository.findById(project);
                if (!optionalProject.isPresent()) {
                    continue;
                } else {
                    ProjectDisplayDTO projectDisplayDTO = new ProjectDisplayDTO();

                    projectDisplayDTO.setProjectId(optionalProject.get().getProjectId());
                    projectDisplayDTO.setProjectName(optionalProject.get().getProjectName());
                    projectDisplayDTO.setProjectStartDate(optionalProject.get().getProjectStartDate());
                    projectDisplayDTO.setProjectEndDate(optionalProject.get().getProjectEndDate());
                    projectDisplayDTO.setProjectDescription(optionalProject.get().getProjectDescription());

                    List<DisplayOneUserDTO> displayOneUserDTOList = new ArrayList<>();
                    for(ProjectAdmins projectAdmins : optionalProject.get().getProjectAdmins()){
                        DisplayOneUserDTO displayOneUserDTOAdmin = userServiceProxy.getOneUser(projectAdmins.getAdminId()).getBody();
                        if(displayMyProjectsDTO != null) {
                            displayOneUserDTOList.add(displayOneUserDTOAdmin);
                        }
                    }
                    projectDisplayDTO.setProjectAdmins(displayOneUserDTOList);

                    Optional<ProjectClient> optionalProjectClient= projectClientsRepository.getProjectClient(project);
                    projectDisplayDTO.setProjectClient(optionalProjectClient.get());

                    List<ProjectTasks> projectTasksList = projectTasksRepository.getUserTasks(project);
                    List<TaskDisplayDTO> taskDisplayDTOS = new ArrayList<>();
                    if (!projectTasksList.isEmpty()) {
                        for (ProjectTasks tasks : projectTasksList) {
                            for (TaskDev taskDevolopers : tasks.getTaskDev()) {
                                if (taskDevolopers.getDevId() == userId) {
                                    TaskDisplayDTO taskDisplayDTO = new TaskDisplayDTO();
                                    taskDisplayDTO.setProjectTaskId(tasks.getProjectTaskId());
                                    taskDisplayDTO.setTaskName(tasks.getTaskName());
                                    taskDisplayDTO.setTaskDescription(tasks.getTaskDescription());
                                    taskDisplayDTO.setTaskStartTime(tasks.getTaskStartTime());
                                    taskDisplayDTO.setTaskEndTime(tasks.getTaskEndTime());

                                    List<DisplayOneUserDTO> displayOneUserDTOS = new ArrayList<>();
                                    for (TaskDev taskDev : tasks.getTaskDev()) {
                                        DisplayOneUserDTO oneUserDTO = userServiceProxy.getOneUser(taskDev.getDevId()).getBody();
                                        if (oneUserDTO != null) {
                                            displayOneUserDTOS.add(oneUserDTO);
                                        }
                                    }
                                    taskDisplayDTO.setTaskDevList(displayOneUserDTOS);
                                    taskDisplayDTOS.add(taskDisplayDTO);
                                }
                            }
                        }
                    }
                    projectDisplayDTO.setProjectTasks(taskDisplayDTOS);
                    devProjects.add(projectDisplayDTO);
                }
            }

            for (int project : myProjectsDTO.getDevAdminProjects().getAdminDev()) {
                Optional<Project> optionalProject = projectRepository.findById(project);
                if (!optionalProject.isPresent()) {
                    continue;
                } else {
                    ProjectDisplayDTO projectDisplayDTO = new ProjectDisplayDTO();

                    projectDisplayDTO.setProjectId(optionalProject.get().getProjectId());
                    projectDisplayDTO.setProjectName(optionalProject.get().getProjectName());
                    projectDisplayDTO.setProjectStartDate(optionalProject.get().getProjectStartDate());
                    projectDisplayDTO.setProjectEndDate(optionalProject.get().getProjectEndDate());
                    projectDisplayDTO.setProjectDescription(optionalProject.get().getProjectDescription());

                    List<DisplayOneUserDTO> displayOneUserDTOList = new ArrayList<>();
                    for(ProjectAdmins projectAdmins : optionalProject.get().getProjectAdmins()){
                        DisplayOneUserDTO displayOneUserDTOAdmin = userServiceProxy.getOneUser(projectAdmins.getAdminId()).getBody();
                        if(displayMyProjectsDTO != null) {
                            displayOneUserDTOList.add(displayOneUserDTOAdmin);
                        }
                    }
                    projectDisplayDTO.setProjectAdmins(displayOneUserDTOList);

                    Optional<ProjectClient> optionalProjectClient= projectClientsRepository.getProjectClient(project);
                    projectDisplayDTO.setProjectClient(optionalProjectClient.get());

                    List<ProjectTasks> projectTasksList = projectTasksRepository.getUserTasks(project);
                    List<TaskDisplayDTO> taskDisplayDTOS = new ArrayList<>();
                    if (!projectTasksList.isEmpty()) {
                        for (ProjectTasks tasks : projectTasksList) {
                            for (TaskDev taskDevolopers : tasks.getTaskDev()) {
                                if (taskDevolopers.getDevId() == userId) {
                                    TaskDisplayDTO taskDisplayDTO = new TaskDisplayDTO();
                                    taskDisplayDTO.setProjectTaskId(tasks.getProjectTaskId());
                                    taskDisplayDTO.setTaskName(tasks.getTaskName());
                                    taskDisplayDTO.setTaskDescription(tasks.getTaskDescription());
                                    taskDisplayDTO.setTaskStartTime(tasks.getTaskStartTime());
                                    taskDisplayDTO.setTaskEndTime(tasks.getTaskEndTime());

                                    List<DisplayOneUserDTO> displayOneUserDTOS = new ArrayList<>();
                                    for (TaskDev taskDev : tasks.getTaskDev()) {
                                        DisplayOneUserDTO oneUserDTO = userServiceProxy.getOneUser(taskDev.getDevId()).getBody();
                                        if (oneUserDTO != null) {
                                            displayOneUserDTOS.add(oneUserDTO);
                                        }
                                    }
                                    taskDisplayDTO.setTaskDevList(displayOneUserDTOS);
                                    taskDisplayDTOS.add(taskDisplayDTO);
                                }
                            }
                        }
                    }
                    projectDisplayDTO.setProjectTasks(taskDisplayDTOS);
                    devAdminProjects.add(projectDisplayDTO);
                }
            }
            displayMyProjectsDTO.setAdminProjects(adminProjects);
            displayMyProjectsDTO.setDevProjects(devProjects);
            displayMyProjectsDTO.setDevAdminProjects(devAdminProjects);

            return new ResponseEntity<>(displayMyProjectsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<?> buildFallbackUser(Principal principal){
        DisplayMyProjectsDTO displayMyProjectsDTO = new DisplayMyProjectsDTO();
        lOGGER.info("fallback by circuit bracker");
        return new ResponseEntity(displayMyProjectsDTO, HttpStatus.OK);
    }

}


