package com.itemsService.proxy;

import com.itemsService.dto.DisplayOneUserDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "user-service")
public interface UserServiceProxy {

    @GetMapping("api/user/staffUser/aqqget-one-user")
    ResponseEntity<DisplayOneUserDTO> getOneUser(@RequestParam("user") int user);

    @GetMapping("api/user/staffUser/test")
    ResponseEntity testFirst();
}
