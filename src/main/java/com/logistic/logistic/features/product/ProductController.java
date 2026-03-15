package com.logistic.logistic.features.product;

import com.logistic.logistic.features.product.dto.ProductRequest;
import com.logistic.logistic.features.product.dto.ProductResponse;
import com.logistic.logistic.features.product.dto.UpdateProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    List<ProductResponse> findAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    ProductResponse findProductById( @PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping
    ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PatchMapping("/{id}")
    ProductResponse updateProduct(@Valid @PathVariable Integer id,@Valid @RequestBody UpdateProductRequest productRequest){
        return productService.updateProduct(productRequest, id);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }
}
