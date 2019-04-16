package com.itemsService.proxy;

import com.itemsService.dto.DisplayOneUserDTO;
import com.itemsService.dto.MyProjectsDTO;
import com.itemsService.dto.ProjectUserDTO;
import com.itemsService.model.ResponseModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "user-service")
public interface UserServiceProxy {

    @GetMapping("api/user/staffUser/get-one-user")
    ResponseEntity<DisplayOneUserDTO> getOneUser(@RequestParam("user") int user);

    @PostMapping("api/user/user-project/dev")
    ResponseEntity<ResponseModel> saveDev(@RequestBody ProjectUserDTO projectUserDTO);

    @PostMapping("api/user/user-project/admin")
    ResponseEntity<ResponseModel> saveAdmin(@RequestBody ProjectUserDTO projectUserDTO);

    @GetMapping("api/user/user-project//my-projects")
    ResponseEntity<MyProjectsDTO> getMyProjects(@RequestParam("user-id") int userId);

    @GetMapping("api/user/staffUser/test")
    ResponseEntity testFirst();
}
