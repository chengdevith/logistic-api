package com.logistic.logistic.mapper;

import com.logistic.logistic.domain.Product;
import com.logistic.logistic.domain.Supplier;
import com.logistic.logistic.features.product.dto.UpdateProductRequest;
import com.logistic.logistic.features.supplier.dto.SupplierRequest;
import com.logistic.logistic.features.supplier.dto.SupplierResponse;
import com.logistic.logistic.features.supplier.dto.UpdateSupplierRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    List<SupplierResponse> fromSuppliers(List<Supplier> suppliers);
    SupplierResponse fromSupplier(Supplier supplier);
    Supplier toSupplier(SupplierRequest supplierRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromSupplierUpdatePartially(UpdateSupplierRequest updateSupplierRequest, @MappingTarget Supplier supplier);
}
