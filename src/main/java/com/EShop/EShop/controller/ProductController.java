package com.EShop.EShop.controller;

import com.EShop.EShop.dto.ProductDto;
import com.EShop.EShop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product")
    private ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    private ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping("/product")
    private ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.saveProduct(productDto));
    }

    @PatchMapping("/product/{id}")
    private ResponseEntity<ProductDto> updateProductByField(@PathVariable Long id, Map<String,Object> fields){
        return ResponseEntity.ok(productService.updateByField(id,fields));
    }

    @DeleteMapping("/product/{id}")
    private ResponseEntity<String> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteById(id));
    }
}
