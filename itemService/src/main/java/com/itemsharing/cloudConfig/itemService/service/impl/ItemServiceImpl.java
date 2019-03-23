package com.itemsharing.cloudConfig.itemService.service.impl;

import com.itemsharing.cloudConfig.itemService.model.Item;
import com.itemsharing.cloudConfig.itemService.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {


    @Override
    public ResponseEntity<?> addItemByUser(Item item, String username) {
        return null;
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
