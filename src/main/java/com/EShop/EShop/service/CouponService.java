package com.EShop.EShop.service;

import com.EShop.EShop.domain.Coupon;
import com.EShop.EShop.dto.CouponDto;
import com.EShop.EShop.exception.RecordAlreadyExistException;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.CouponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;

    CouponService(CouponRepository couponRepository, ModelMapper modelMapper){
        this.couponRepository = couponRepository;
        this.modelMapper = modelMapper;
    }

    public CouponDto getByCouponId(Long id){
        return toDto(findById(id));
    }

    public CouponDto getByCouponCode(String code){
        return toDto(couponRepository.findCouponByCode(code).orElseThrow(
                ()->new RecordNotFoundException(String.format("No Coupon was found for code: %s",code))
        ));
    }

    public List<CouponDto> getAllCoupons(){
        return couponRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public CouponDto saveCoupon(CouponDto couponDto){
        Coupon c = toDo(couponDto);
        Optional<Coupon> coupon = couponRepository.findCouponByCode(c.getCode());
        if(coupon.isEmpty()){
            return toDto(couponRepository.save(c));
        }
        if(!coupon.get().getIsActive()){
            coupon.get().setIsActive(Boolean.TRUE);
            return toDto(couponRepository.save(coupon.get()));
        }
        throw new RecordAlreadyExistException("Coupon already exist");
    }

    public CouponDto updateCoupon(Long id, Map<String,Object> fields){
        Coupon c = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Coupon.class,key);
            field.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(field,c,value);
        });
        return toDto(couponRepository.save(c));
    }

    public CouponDto inActiveCoupon(Long id){
        Coupon c = findById(id);
        c.setIsActive(Boolean.FALSE);
        return toDto(couponRepository.save(c));
    }

    public String deleteCoupon(Long id){
        couponRepository.deleteById(id);
        return "Coupon Deleted";
    }

    private Coupon findById(Long id){
        return couponRepository.findById(id).orElseThrow(
                ()->new RecordNotFoundException(String.format("Coupon not found on Id: %d",id))
        );
    }

    private Coupon toDo(CouponDto couponDto){
        return modelMapper.map(couponDto,Coupon.class);
    }

    private CouponDto toDto(Coupon coupon){
        return modelMapper.map(coupon,CouponDto.class);
    }
}
