package com.logistic.logistic.features.inventory;

import com.logistic.logistic.domain.Inventory;
import com.logistic.logistic.domain.Product;
import com.logistic.logistic.features.inventory.dto.InventoryRequest;
import com.logistic.logistic.features.inventory.dto.InventoryResponse;
import com.logistic.logistic.features.product.ProductRepository;
import com.logistic.logistic.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ProductRepository productRepository;

    @Override
    public List<InventoryResponse> getAllInventory() {
        return inventoryMapper.fromInventories(inventoryRepository.findAll());
    }

    @Override
    public InventoryResponse getInventoryById(Integer id) {
        return inventoryMapper.fromInventory(inventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory " + id +" Not found")
                ));
    }

    @Override
    public InventoryResponse createInventory(InventoryRequest inventoryRequest) {
        Product product = productRepository.findById(inventoryRequest.productId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + inventoryRequest.productId() +" Not found"));
        Inventory inventory = inventoryMapper.toInventory(inventoryRequest);
        inventory.setProduct(product);
        return inventoryMapper.fromInventory(inventoryRepository.save(inventory));
    }
}
