package com.EShop.EShop.controller;

import com.EShop.EShop.domain.Cart;
import com.EShop.EShop.dto.CartDto;
import com.EShop.EShop.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/cart/{id}")
    private ResponseEntity<CartDto> getCartById(@PathVariable Long id){
        return ResponseEntity.ok(cartService.getById(id));
    }

//    @GetMapping("/cart/{deviceAddress}")
//    private ResponseEntity<CartDto> getByDeviceAddress(@PathVariable String deviceAddress){
//        return ResponseEntity.ok(cartService.getCartByDeviceAddress(deviceAddress));
//    }

    @PostMapping("/cart")
    private ResponseEntity<CartDto> saveCart(@Valid @RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.saveCart(cartDto));
    }

    @PatchMapping("/cart/{id}")
    private ResponseEntity<CartDto> updateCart(@PathVariable Long id,@RequestBody Map<String, Object> fields){
        return ResponseEntity.ok(cartService.updateCart(id,fields));
    }

}
