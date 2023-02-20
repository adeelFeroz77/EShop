package com.EShop.EShop.service;

import com.EShop.EShop.domain.Category;
import com.EShop.EShop.dto.CategoryDto;
import com.EShop.EShop.exception.RecordAlreadyExistException;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.categoryRepository=categoryRepository;
        this.modelMapper=modelMapper;
    }

    public CategoryDto getById(Long id){
        Category c = categoryRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(String.format("Category not found on id:%d ",id))
        );
        return toDto(c);
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAllByIsActive(Boolean.TRUE);
        return categories.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CategoryDto saveCategory(CategoryDto categoryDto){
        Category category = toDo(categoryDto);
        Optional<Category> c = categoryRepository.findByName(category.getName());
        if(c.isEmpty()){
            return toDto(categoryRepository.save(category));
        } else if (!c.get().getIsActive()) {
            c.get().setIsActive(Boolean.TRUE);
            return toDto(categoryRepository.save(c.get()));
        }
        throw new RecordAlreadyExistException("Record Already Exists!");
    }

    public CategoryDto updateByField(Long id, Map<String, Object> fields){
        Category c = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Category.class,key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,c,value);
        });
        return toDto(categoryRepository.save(c));
    }

    public CategoryDto updateCategory(Long id,CategoryDto categoryDto){
        Category category = toDo(categoryDto);
        Category c = findById(id);
        c.setIsActive(category.getIsActive());
        c.setName((category.getName()));
        return toDto(categoryRepository.save(c));
    }

    public String deleteById(Long id){
        Category c = findById(id);
        c.setIsActive(Boolean.FALSE);
        categoryRepository.save(c);
        return String.format("Category With ID:%d Deleted Successfully",id);
    }


    public Category toDo(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

    public CategoryDto toDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }

    private Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(String.format("Category not found on Id: %d",id))
        );
    }

}
