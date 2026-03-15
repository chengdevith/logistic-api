package com.logistic.logistic.mapper;

import com.logistic.logistic.domain.Product;
import com.logistic.logistic.features.product.dto.ProductRequest;
import com.logistic.logistic.features.product.dto.ProductResponse;
import com.logistic.logistic.features.product.dto.UpdateProductRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromProduct(ProductRequest productRequest);
    List<ProductResponse> toProducts(List<Product> products);
    ProductResponse toProduct(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromProductUpdatePartially(UpdateProductRequest updateProductRequest, @MappingTarget Product product);
}
