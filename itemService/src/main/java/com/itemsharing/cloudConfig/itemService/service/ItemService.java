package com.itemsharing.cloudConfig.itemService.service;

import com.itemsharing.cloudConfig.itemService.model.Item;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ItemService {

    ResponseEntity<?> addItemByUser(Item item, String username);
    ResponseEntity<?> getAllItems();
    ResponseEntity<?> getItemsByUserName(String username);
    ResponseEntity<?> GetItemById(Long id);
    ResponseEntity<?> updateItem(Item item) throws IOException;
    ResponseEntity<?> deleteItemById(long id);

}
