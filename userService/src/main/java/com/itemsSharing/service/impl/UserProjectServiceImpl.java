package com.itemsSharing.service.impl;

import com.itemsSharing.dto.*;
import com.itemsSharing.model.*;
import com.itemsSharing.repository.AdminProjectsRepository;
import com.itemsSharing.repository.DevAdminProjectsRepository;
import com.itemsSharing.repository.DevProjectsRepository;
import com.itemsSharing.repository.UserRepository;
import com.itemsSharing.service.UserProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserProjectServiceImpl implements UserProjectsService {

    @Autowired
    AdminProjectsRepository adminProjectsRepository;

    @Autowired
    DevProjectsRepository devProjectsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DevAdminProjectsRepository devAdminProjectsRepository;

    @SuppressWarnings("Duplicates")
    @Override
    public ResponseEntity<?> saveProjectDev(ProjectUserDTO projectUserDTO){
        Optional<User> optionalUser = userRepository.findById(projectUserDTO.getUserid());
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied user Id",false), HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        Optional<AdminProjects> adminProjectsOptional = adminProjectsRepository.getAdminProjects(projectUserDTO.getUserid(),projectUserDTO.getProjectId());
        Optional<DevProjects> devProjectsOptional = devProjectsRepository.getDevProjects(projectUserDTO.getUserid(),projectUserDTO.getProjectId());
        if(adminProjectsOptional.isPresent()){
            adminProjectsRepository.delete(adminProjectsOptional.get());

            List<DevAdminProjects> devAdminProjectsList = new ArrayList<>();
            if(!user.getDevAdminProjects().isEmpty()){
                devAdminProjectsList = user.getDevAdminProjects();
            }
            DevAdminProjects devAdminProjects = new DevAdminProjects();
            devAdminProjects.setDevAdmin(user);
            devAdminProjects.setProjectId(projectUserDTO.getProjectId());
            try {
                devAdminProjects = devAdminProjectsRepository.save(devAdminProjects);
            } catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
            devAdminProjectsList.add(devAdminProjects);
            user.setDevAdminProjects(devAdminProjectsList);
            try {
                userRepository.save(user);
                return new ResponseEntity<>(new ResponseModel("project saved successfully",true),HttpStatus.OK);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else if(devProjectsOptional.isPresent()){
            return new ResponseEntity<>(new ResponseModel("dev already exists successfully",true),HttpStatus.OK);
        } else {

            List<DevProjects> devProjects = new ArrayList<>();
            if (!user.getDevProjects().isEmpty()) {
                devProjects = user.getDevProjects();
            }

            DevProjects projects = new DevProjects();
            projects.setProjectId(projectUserDTO.getProjectId());
            projects.setDev(user);

            try {
                projects = devProjectsRepository.save(projects);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            devProjects.add(projects);
            user.setDevProjects(devProjects);

            try {
                userRepository.save(user);
                return new ResponseEntity<>(new ResponseModel("project saved successfully", true), HttpStatus.OK);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public ResponseEntity<?> getAllMyProjects(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            MyProjectsDTO myProjectsDTO = new MyProjectsDTO();

            List<Integer> adminProjectsDTOS = new ArrayList<>();
            List<Integer> devProjectsDTOS = new ArrayList<>();
            List<Integer> adminDevProjectsDTOS = new ArrayList<>();

            if(!optionalUser.get().getAdminProjects().isEmpty()){
                for(AdminProjects projects : optionalUser.get().getAdminProjects()){
                    int id = projects.getProjectId();
                    adminProjectsDTOS.add(id);
                }
            } if(!optionalUser.get().getDevProjects().isEmpty()){
                for(DevProjects projects : optionalUser.get().getDevProjects()){
                    int id = projects.getProjectId();
                    devProjectsDTOS.add(id);
                }
            } if(!optionalUser.get().getDevAdminProjects().isEmpty()){
                for(DevAdminProjects projects : optionalUser.get().getDevAdminProjects()){
                    int id = projects.getProjectId();
                    adminDevProjectsDTOS.add(id);
                }
            } if(optionalUser.get().getAdminProjects().isEmpty() && !optionalUser.get().getDevProjects().isEmpty() && !optionalUser.get().getDevAdminProjects().isEmpty()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            AdminProjectsDTO adminProjectsDTO = new AdminProjectsDTO();
            adminProjectsDTO.setAdminProjects(adminProjectsDTOS);

            DevProjectsDTO devProjectsDTO = new DevProjectsDTO();
            devProjectsDTO.setDevProjects(devProjectsDTOS);

            AdminDevProjects adminDevProjects = new AdminDevProjects();
            adminDevProjects.setAdminDev(adminDevProjectsDTOS);

            myProjectsDTO.setAdminProjectsList(adminProjectsDTO);
            myProjectsDTO.setDevProjects(devProjectsDTO);
            myProjectsDTO.setDevAdminProjects(adminDevProjects);
            myProjectsDTO.setName(optionalUser.get().getName());
            myProjectsDTO.setEmail(optionalUser.get().getEmail());
            myProjectsDTO.setUserRole(optionalUser.get().getUserRole().getUserRoleType());
            myProjectsDTO.setUsername(optionalUser.get().getUsername());
            return new ResponseEntity<>(myProjectsDTO,HttpStatus.OK);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public ResponseEntity<?> saveProjectAdmin(ProjectUserDTO projectUserDTO){
        Optional<User> optionalUser = userRepository.findById(projectUserDTO.getUserid());
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied user Id",false), HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();
        Optional<AdminProjects> adminProjectsOptional = adminProjectsRepository.getAdminProjects(projectUserDTO.getUserid(),projectUserDTO.getProjectId());
        Optional<DevProjects> devProjectsOptional = devProjectsRepository.getDevProjects(projectUserDTO.getUserid(),projectUserDTO.getProjectId());
        if(devProjectsOptional.isPresent()){
            devProjectsRepository.delete(devProjectsOptional.get());

            List<DevAdminProjects> devAdminProjectsList = new ArrayList<>();
            if(!user.getDevAdminProjects().isEmpty()){
                devAdminProjectsList = user.getDevAdminProjects();
            }
            DevAdminProjects devAdminProjects = new DevAdminProjects();
            devAdminProjects.setDevAdmin(user);
            devAdminProjects.setProjectId(projectUserDTO.getProjectId());
            try {
                devAdminProjects = devAdminProjectsRepository.save(devAdminProjects);
            } catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
            devAdminProjectsList.add(devAdminProjects);
            user.setDevAdminProjects(devAdminProjectsList);
            try {
                userRepository.save(user);
                return new ResponseEntity<>(new ResponseModel("project saved successfully",true),HttpStatus.OK);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else if (adminProjectsOptional.isPresent()){
            return new ResponseEntity<>(new ResponseModel("project already admin",true),HttpStatus.OK);
        } else {

            List<AdminProjects> adminProjects = new ArrayList<>();
            if (!user.getAdminProjects().isEmpty()) {
                adminProjects = user.getAdminProjects();
            }

            AdminProjects projects = new AdminProjects();
            projects.setProjectId(projectUserDTO.getProjectId());
            projects.setAdmin(user);

            try {
                projects = adminProjectsRepository.save(projects);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            adminProjects.add(projects);
            user.setAdminProjects(adminProjects);

            try {
                userRepository.save(user);
                return new ResponseEntity<>(new ResponseModel("project saved successfully", true), HttpStatus.OK);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
