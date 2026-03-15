package com.logistic.logistic.features.inventory;

import com.logistic.logistic.features.inventory.dto.InventoryRequest;
import com.logistic.logistic.features.inventory.dto.InventoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    List<InventoryResponse> findAllInventory(){
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    InventoryResponse findInventoryById(@PathVariable Integer id){
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    InventoryResponse createInventory(@Valid @RequestBody InventoryRequest inventoryRequest){
        return inventoryService.createInventory(inventoryRequest);
    }
}
