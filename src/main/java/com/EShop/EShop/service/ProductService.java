package com.EShop.EShop.service;

import com.EShop.EShop.domain.Product;
import com.EShop.EShop.dto.ProductDto;
import com.EShop.EShop.exception.RecordAlreadyExistException;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto getById(Long id){
        return toDto(findById(id));
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAllByIsActive(Boolean.TRUE);
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductDto saveProduct(ProductDto productDto){
        Product p = toDo(productDto);
        Optional<Product> product = productRepository.findByNameAndCategoryAndModel(p.getName(),p.getCategory(),p.getModel());
        if(product.isEmpty()){
            return toDto(productRepository.save(p));
        }else if(!product.get().getIsActive()){
            product.get().setIsActive(Boolean.TRUE);
            return toDto(productRepository.save(product.get()));
        }
        throw new RecordAlreadyExistException("Record Already Exist");
    }

    public ProductDto updateByField(Long id, Map<String,Object> fields){
        Product product = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Product.class,key);
            field.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(field,product,value);
        });
        return toDto(productRepository.save(product));
    }

    public String deleteById(Long id){
        Product product = findById(id);
        product.setIsActive(Boolean.FALSE);
        productRepository.save(product);
        return String.format("Product With ID:%d Deleted Successfully",id);
    }

    private Product findById(Long id){
        return productRepository.findById(id).orElseThrow(
                ()->new RecordNotFoundException(String.format("Record not found on id:%d",id))
        );
    }

    private Product toDo(ProductDto productDto){
       return modelMapper.map(productDto,Product.class);
    }

    private ProductDto toDto(Product product){
        return modelMapper.map(product,ProductDto.class);
    }
}
