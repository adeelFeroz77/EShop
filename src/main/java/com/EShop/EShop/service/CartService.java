package com.EShop.EShop.service;

import com.EShop.EShop.domain.Cart;
import com.EShop.EShop.dto.CartDto;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    CartService(CartRepository cartRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.cartRepository = cartRepository;
    }

    public CartDto getById(Long id){
        return toDto(findById(id));
    }

    public CartDto getCartByDeviceAddress(String deviceAddress){
        return toDto(cartRepository.findByDeviceAddress(deviceAddress));
    }

    public CartDto saveCart(CartDto cartDto){
        Cart c = toDo(cartDto);
        return toDto(cartRepository.save(c));
    }

    public CartDto updateCart(Long id, Map<String,Object> fields){
        Cart c = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Cart.class,key);
            field.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(field,c,value);
        });
        return toDto(cartRepository.save(c));
    }

    private Cart findById(Long id){
        return cartRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(
                String.format("Cart not found on Id:%d",id)
        ));
    }

    private Cart toDo(CartDto cartDto){
        return modelMapper.map(cartDto,Cart.class);
    }

    private CartDto toDto(Cart cart){
        return modelMapper.map(cart,CartDto.class);
    }

}
