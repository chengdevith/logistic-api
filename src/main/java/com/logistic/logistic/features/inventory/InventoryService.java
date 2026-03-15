package com.logistic.logistic.features.inventory;

import com.logistic.logistic.features.inventory.dto.InventoryRequest;
import com.logistic.logistic.features.inventory.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> getAllInventory();
    InventoryResponse getInventoryById(Integer id);
    InventoryResponse createInventory(InventoryRequest inventoryRequest);
}
