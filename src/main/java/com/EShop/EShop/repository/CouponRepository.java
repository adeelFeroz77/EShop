package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    public Optional<Coupon> findCouponByCode(String code);
    public Coupon findAllByIsActive(Boolean isActive);

}
