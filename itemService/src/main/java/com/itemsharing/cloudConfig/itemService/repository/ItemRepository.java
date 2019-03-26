package com.itemsharing.cloudConfig.itemService.repository;

import com.itemsharing.cloudConfig.itemService.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
