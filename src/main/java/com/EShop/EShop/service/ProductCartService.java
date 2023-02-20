package com.EShop.EShop.service;

import com.EShop.EShop.domain.Cart;
import com.EShop.EShop.domain.ProductCart;
import com.EShop.EShop.dto.ProductCartDto;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.ProductCartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCartService {

    private final ProductCartRepository productCartRepository;
    private final ModelMapper modelMapper;

    public ProductCartService(ProductCartRepository productCartRepository, ModelMapper modelMapper){
        this.productCartRepository = productCartRepository;
        this.modelMapper = modelMapper;
    }

    public ProductCartDto saveProductCart(ProductCartDto productCartDto){
        ProductCart productCart = toDo(productCartDto);
        Optional<ProductCart> pc = productCartRepository.findByCartAndProduct(productCart.getCart(),productCart.getProduct());
        if(pc.isPresent()){
            pc.get().setQuantity(pc.get().getQuantity()+productCart.getQuantity());
            return toDto(productCartRepository.save(pc.get()));
        }
        return toDto(productCartRepository.save(productCart));
    }

    public ProductCartDto deleteItem(ProductCartDto productCartDto){
        ProductCart productCart = toDo(productCartDto);
        if (productCart.getQuantity()==1){
            productCartRepository.deleteByCartAndProduct(productCart.getCart(),productCart.getProduct());
            return productCartDto;
        }
        Optional<ProductCart> p = productCartRepository.findByCartAndProduct(productCart.getCart(),productCart.getProduct());
        if(p.isEmpty()){
            throw new RecordNotFoundException("Not Found");
        }
        p.get().setQuantity(productCart.getQuantity()-1);
        return toDto(productCartRepository.save(p.get()));
    }

    public List<ProductCartDto> findAllByCart(Long cartId){
        List<ProductCart> productCarts = productCartRepository.findAllByCart(cartId);
        return productCarts.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ProductCartDto> findAllByProduct(ProductCartDto productCartDto){
        ProductCart productCart = toDo(productCartDto);
        List<ProductCart> productCarts = productCartRepository.findAllByProduct(productCart.getProduct());
        return productCarts.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductCartDto findByCartAndProduct(ProductCartDto productCartDto){
        ProductCart productCart = toDo(productCartDto);
        ProductCart p= productCartRepository.findByCartAndProduct(productCart.getCart(),productCart.getProduct()).orElseThrow(
                ()->new RecordNotFoundException("Record not found for specified product and cart")
        );
        return toDto(p);
    }

    private ProductCart toDo(ProductCartDto productCartDto){
        return modelMapper.map(productCartDto,ProductCart.class);
    }

    private ProductCartDto toDto(ProductCart productCart){
        return modelMapper.map(productCart,ProductCartDto.class);
    }
}
