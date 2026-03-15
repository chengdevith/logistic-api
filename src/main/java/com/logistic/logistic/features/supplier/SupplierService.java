package com.logistic.logistic.features.supplier;

import com.logistic.logistic.features.supplier.dto.SupplierRequest;
import com.logistic.logistic.features.supplier.dto.SupplierResponse;
import com.logistic.logistic.features.supplier.dto.UpdateSupplierRequest;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> findAllSupplier();
    SupplierResponse findSupplierById(Integer id);
    SupplierResponse createSupplier(SupplierRequest supplierRequest);
    SupplierResponse updateSupplier(Integer id, UpdateSupplierRequest updateSupplierRequest);
}
