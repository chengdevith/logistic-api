package com.logistic.logistic.features.product;

import com.logistic.logistic.features.product.dto.ProductRequest;
import com.logistic.logistic.features.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts ();
    ProductResponse getProductById(Integer id);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productRequest, Integer id);
    void deleteProduct(Integer id);
}
