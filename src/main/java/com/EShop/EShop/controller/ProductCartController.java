package com.EShop.EShop.controller;

import com.EShop.EShop.dto.ProductCartDto;
import com.EShop.EShop.service.ProductCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCartController {

    private final ProductCartService productCartService;

    ProductCartController(ProductCartService productCartService){
        this.productCartService = productCartService;
    }

    @GetMapping("productCart/{cart_id}")
    private List<ProductCartDto> getAllByCart(@PathVariable("cart_id") Long id){
        return productCartService.findAllByCart(id);
    }
}
