package com.itemsharing.cloudConfig.itemService.repository;

import com.itemsharing.cloudConfig.itemService.service.ItemService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemService,Long> {


}
