package com.logistic.logistic.features.product;

import com.logistic.logistic.domain.Product;
import com.logistic.logistic.features.product.dto.ProductRequest;
import com.logistic.logistic.features.product.dto.ProductResponse;
import com.logistic.logistic.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MapperBuilder mapperBuilder;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productMapper.toProducts(productRepository.findAll());
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        return productMapper.toProduct(productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product " + id + " Not found"
                )));
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.fromProduct(productRequest);
        productRepository.save(product);
        return productMapper.toProduct(product);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + id + " not found"));
        productMapper.fromProductUpdatePartially(productRequest, product);
        productRepository.save(product);
        return productMapper.toProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
