package com.itemsService.service.impl;

import com.itemsService.dto.AdminDTO;
import com.itemsService.dto.DisplayOneUserDTO;
import com.itemsService.dto.ProjectDTO;
import com.itemsService.model.Project;
import com.itemsService.model.ProjectAdmins;
import com.itemsService.model.ResponseModel;
import com.itemsService.proxy.UserServiceProxy;
import com.itemsService.repository.ProjectAdminsRepository;
import com.itemsService.repository.ProjectRepository;
import com.itemsService.service.ProjectAdminsService;
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
public class ProjectAdminsServiceImpl implements ProjectAdminsService {

    @Autowired
    UserServiceProxy userServiceProxy;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectAdminsRepository projectAdminsRepository;

    public ResponseEntity<?> test() {
        return new ResponseEntity(userServiceProxy.getMyProjects(3).getBody(), HttpStatus.OK);
    }

    private static final Logger lOGGER = LoggerFactory.getLogger(ProjectAdminsServiceImpl.class);

    @LoadBalanced
    @HystrixCommand(
            fallbackMethod = "buildFallbackUser",
            threadPoolKey = "adminsByUserThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            }
    )
    public ResponseEntity<?> saveProjectAdmins(AdminDTO adminDTO){

        Optional<Project> optionalProject = projectRepository.findById(adminDTO.getProjectId());

        if(!optionalProject.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid project details",false),HttpStatus.BAD_REQUEST);
        }

        List<ProjectAdmins> projectAdminsList = optionalProject.get().getProjectAdmins();
        List<DisplayOneUserDTO> displayOneUserDTOS = new ArrayList<>();
        for(int i : adminDTO.getAdminIds()){
            DisplayOneUserDTO displayOneUserDTO =  userServiceProxy.getOneUser(i).getBody();
            if(displayOneUserDTO == null){
                return new ResponseEntity<>(new ResponseModel("Invalid user id",false),HttpStatus.BAD_REQUEST);
            }else {
                ProjectAdmins projectAdmins = new ProjectAdmins();
                projectAdmins.setAdminId(i);
                projectAdmins.setProject(optionalProject.get());
                try {
                    projectAdmins = projectAdminsRepository.save(projectAdmins);
                    projectAdminsList.add(projectAdmins);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                displayOneUserDTOS.add(displayOneUserDTO);
            }
        }

        Project project = optionalProject.get();
        project.setProjectAdmins(projectAdminsList);
        return new ResponseEntity<>(displayOneUserDTOS,HttpStatus.CREATED);
    }

    @SuppressWarnings("Duplicates")
    public ResponseEntity<?> buildFallbackUser(AdminDTO adminDTO){
        Optional<Project> optionalProject = projectRepository.findById(adminDTO.getProjectId());

        if(!optionalProject.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid project details",false),HttpStatus.BAD_REQUEST);
        }
        List<ProjectAdmins> projectAdminsList = optionalProject.get().getProjectAdmins();
        lOGGER.info("fallback by circuit bracker");
        return new ResponseEntity(projectAdminsList, HttpStatus.OK);
    }

}
