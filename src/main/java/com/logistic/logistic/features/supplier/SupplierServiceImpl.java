package com.logistic.logistic.features.supplier;

import com.logistic.logistic.domain.Product;
import com.logistic.logistic.domain.Supplier;
import com.logistic.logistic.features.supplier.dto.SupplierRequest;
import com.logistic.logistic.features.supplier.dto.SupplierResponse;
import com.logistic.logistic.features.supplier.dto.UpdateSupplierRequest;
import com.logistic.logistic.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService{

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public List<SupplierResponse> findAllSupplier() {
        return supplierMapper.fromSuppliers(supplierRepository.findAll());
    }

    @Override
    public SupplierResponse findSupplierById(Integer id) {
        return supplierMapper.fromSupplier(supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + id + " not found"))
        );
    }

    @Override
    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = supplierMapper.toSupplier(supplierRequest);
        supplierRepository.save(supplier);
        return supplierMapper.fromSupplier(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(Integer id, UpdateSupplierRequest updateSupplierRequest) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + id + " not found"));
        supplierMapper.fromSupplierUpdatePartially(updateSupplierRequest, supplier);
        supplierRepository.save(supplier);
        return supplierMapper.fromSupplier(supplier);
    }
}
