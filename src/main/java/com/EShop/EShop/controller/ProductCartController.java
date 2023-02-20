package com.EShop.EShop.controller;

import com.EShop.EShop.dto.ProductCartDto;
import com.EShop.EShop.service.ProductCartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCartController {

    private final ProductCartService productCartService;

    ProductCartController(ProductCartService productCartService){
        this.productCartService = productCartService;
    }

//    @GetMapping("productCart/{cart_id}")
//    private List<ProductCartDto> getAllByCart(@PathVariable("cart_id") Long id){
//        return productCartService.findAllByCart(id);
//    }

    @PostMapping("/productcart")
    private ResponseEntity<ProductCartDto> saveProductCart(@Valid @RequestBody ProductCartDto productCartDto){
        return ResponseEntity.ok(productCartService.saveProductCart(productCartDto));
    }
}
