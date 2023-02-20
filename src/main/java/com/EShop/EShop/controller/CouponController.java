package com.EShop.EShop.controller;

import com.EShop.EShop.dto.CouponDto;
import com.EShop.EShop.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CouponController {

    private final CouponService couponService;

    CouponController(CouponService couponService){
        this.couponService=couponService;
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponDto> getCouponById(@PathVariable Long id){
        return ResponseEntity.ok(couponService.getByCouponId(id));
    }

    @GetMapping("/coupon")
    public ResponseEntity<List<CouponDto>> getAllCoupons(){
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/coupon/{code}")
    public ResponseEntity<CouponDto> getCouponByCode(@PathVariable String code){
        return ResponseEntity.ok(couponService.getByCouponCode(code));
    }

    @PostMapping("/coupon")
    public ResponseEntity<CouponDto> saveCoupon(@Valid @RequestBody CouponDto couponDto){
        return ResponseEntity.ok(couponService.saveCoupon(couponDto));
    }

    @PatchMapping("/coupon/{id}")
    public ResponseEntity<CouponDto> updateCoupon(@PathVariable Long id, Map<String,Object> fields){
        return ResponseEntity.ok(couponService.updateCoupon(id,fields));
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id){
        return ResponseEntity.ok(couponService.deleteCoupon(id));
    }
}
