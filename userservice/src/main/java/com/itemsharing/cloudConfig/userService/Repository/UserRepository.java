package com.itemsharing.cloudConfig.userService.Repository;

import com.itemsharing.cloudConfig.userService.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {



    Optional<User> findByUsername(String username);
}
