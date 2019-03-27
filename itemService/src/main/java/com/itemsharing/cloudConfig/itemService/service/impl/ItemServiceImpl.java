package com.itemsharing.cloudConfig.itemService.service.impl;

import com.itemsharing.cloudConfig.itemService.model.Item;
import com.itemsharing.cloudConfig.itemService.model.User;
import com.itemsharing.cloudConfig.itemService.proxy.UserServiceProxy;
import com.itemsharing.cloudConfig.itemService.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private UserServiceProxy userServiceProxy;


//    @Override
//    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
//    public ResponseEntity<?> addItemByUser(Item item, String username) {
//        User user =  userServiceProxy.getUserByUserName(username).getBody();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @Override
    @HystrixCommand(
            fallbackMethod = "buildFallbackUser",
            threadPoolKey = "itemByUserThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            }
    )
    public ResponseEntity<?> addItemByUser(Item item, String username) {
        User user =  userServiceProxy.getUserByUserName(username).getBody();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private ResponseEntity<?> buildFallbackUser(Item item, String username){
        User user = new User();
        user.setUsername(username);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllItems() {
        return null;
    }

    @Override
    public ResponseEntity<?> getItemsByUserName(String username) {
        return null;
    }

    @Override
    public ResponseEntity<?> GetItemById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateItem(Item item) throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteItemById(long id) {
        return null;
    }
}
