package com.logistic.logistic.mapper;

import com.logistic.logistic.domain.Inventory;
import com.logistic.logistic.features.inventory.dto.InventoryRequest;
import com.logistic.logistic.features.inventory.dto.InventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    List<InventoryResponse> fromInventories(List<Inventory> inventories);
    InventoryResponse fromInventory(Inventory inventory);

    @Mapping(target = "product", ignore = true)
    Inventory toInventory(InventoryRequest inventoryRequest);
}
