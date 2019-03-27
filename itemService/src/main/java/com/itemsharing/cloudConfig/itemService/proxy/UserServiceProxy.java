package com.itemsharing.cloudConfig.itemService.proxy;

import com.itemsharing.cloudConfig.itemService.DTO.UserDisplayDTO;
import com.itemsharing.cloudConfig.itemService.model.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "user-service")
public interface UserServiceProxy {

    @GetMapping("user-service/user/{username}")
    ResponseEntity<User> getUserByUserName(@PathVariable String username);

}
