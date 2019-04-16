package com.itemsService.service.impl;

import com.itemsService.dto.DisplayOneUserDTO;
import com.itemsService.dto.ProjectDTO;
import com.itemsService.dto.ProjectUserDTO;
import com.itemsService.dto.TecnologiesDTO;
import com.itemsService.model.*;
import com.itemsService.proxy.UserServiceProxy;
import com.itemsService.repository.ProjectAdminsRepository;
import com.itemsService.repository.ProjectClientsRepository;
import com.itemsService.repository.ProjectRepository;
import com.itemsService.repository.ProjectTecnologiesRepository;
import com.itemsService.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectClientsRepository projectClientsRepository;

    @Autowired
    ProjectAdminsRepository projectAdminsRepository;

    @Autowired
    ProjectTecnologiesRepository projectTecnologiesRepository;

    @Autowired
    UserServiceProxy userServiceProxy;

    private static final Logger lOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @LoadBalanced
    @HystrixCommand(
            fallbackMethod = "buildFallbackUser",
            threadPoolKey = "itemByUserThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            }
    )
    public ResponseEntity<?> projectSave(ProjectDTO projectDTO, Principal principal){
        int userId = Integer.parseInt(principal.getName());
        DisplayOneUserDTO displayOneUserDTO =  userServiceProxy.getOneUser(userId).getBody();
        if(displayOneUserDTO == null){
            lOGGER.info("invalid user id");
            return new ResponseEntity<>(new ResponseModel("Invalid User Id",false),HttpStatus.BAD_REQUEST);
        } else {
           Project project = new Project();
           project.setProjectName(projectDTO.getProjectName());
           project.setProjectStartDate(projectDTO.getProjectStartDate());
           project.setProjectEndDate(projectDTO.getProjectEndDate());
           project.setProjectDescription(projectDTO.getProjectDescription());


            try {
                project = projectRepository.save(project);
                lOGGER.info("project base save");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            ProjectAdmins projectAdmins = new ProjectAdmins();
            projectAdmins.setAdminId(userId);
            projectAdmins.setProject(project);

            try {
                projectAdmins = projectAdminsRepository.save(projectAdmins);
                lOGGER.info("project admin save");
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<ProjectAdmins> projectAdminsList = new ArrayList<>();
            projectAdminsList.add(projectAdmins);

           ProjectClient projectClient = new ProjectClient();
           projectClient.setClientName(projectDTO.getClientDetails().getClientName());
           projectClient.setClientEmail(projectDTO.getClientDetails().getClientEmail());
           projectClient.setClientMobile(projectDTO.getClientDetails().getClientMobile());
           projectClient.setClientWebUri(projectDTO.getClientDetails().getClientWebUri());
           projectClient.setProject(project);

            try {
                projectClient = projectClientsRepository.save(projectClient);
                lOGGER.info("project client save");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            List<ProjectTecnologies> projectTecnologies = new ArrayList<>();

            for(TecnologiesDTO tecnologiesDTO : projectDTO.getTecnologiesDetails()){
                ProjectTecnologies projectTecnologie = new ProjectTecnologies();
                projectTecnologie.setTecnologyName(tecnologiesDTO.getTecnologyName());
                projectTecnologie.setTecnologyType(tecnologiesDTO.getTecnologyType());
                projectTecnologie.setProject(project);
                try {
                    projectTecnologie = projectTecnologiesRepository.save(projectTecnologie);
                    lOGGER.info("project technologies save");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                projectTecnologies.add(projectTecnologie);
            }

            project.setProjectClient(projectClient);
            project.setTecnologies(projectTecnologies);
            project.setProjectAdmins(projectAdminsList);

            ProjectUserDTO projectUserDTO = new ProjectUserDTO();
            projectUserDTO.setProjectId(project.getProjectId());
            projectUserDTO.setUserid(userId);

            try {
                project = projectRepository.save(project);
                ResponseModel responseModel = userServiceProxy.saveAdmin(projectUserDTO).getBody();
                if(responseModel.isStatus()){
                    lOGGER.info(responseModel.getMessage());
                } else {
                    lOGGER.error(responseModel.getMessage());
                }
                lOGGER.info("final project save");
                return new ResponseEntity<>(project,HttpStatus.CREATED);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> getOneProject(int projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(!optionalProject.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(optionalProject.get(),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> gellAllProjects() {
        return new ResponseEntity<>(projectRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> buildFallbackUser(ProjectDTO projectDTO, Principal principal){
        Project user = new Project();
        lOGGER.info("fallback by circuit bracker");
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
