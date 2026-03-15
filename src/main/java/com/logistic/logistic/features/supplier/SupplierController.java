package com.logistic.logistic.features.supplier;

import com.logistic.logistic.features.supplier.dto.SupplierRequest;
import com.logistic.logistic.features.supplier.dto.SupplierResponse;
import com.logistic.logistic.features.supplier.dto.UpdateSupplierRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    List<SupplierResponse> findAllSupplier(){
        return supplierService.findAllSupplier();
    }

    @GetMapping("/{id}")
    SupplierResponse findSupplierById(@PathVariable Integer id){
        return supplierService.findSupplierById(id);
    }

    @PostMapping
    SupplierResponse createSupplier(@Valid @RequestBody SupplierRequest supplierRequest){
        return supplierService.createSupplier(supplierRequest);
    }

    @PatchMapping("/{id}")
    SupplierResponse updateSupplier(@PathVariable Integer id, @Valid @RequestBody UpdateSupplierRequest updateSupplierRequest){
        return supplierService.updateSupplier(id, updateSupplierRequest);
    }
}
