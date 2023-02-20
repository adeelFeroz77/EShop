package com.EShop.EShop.controller;

import com.EShop.EShop.domain.Category;
import com.EShop.EShop.dto.CategoryDto;
import com.EShop.EShop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{id}")
    private ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/category")
    private ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/category")
    private ResponseEntity<CategoryDto> saveBrand(@Valid @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.saveCategory(categoryDto));
    }

//    @PutMapping("/category/{id}")
//    private ResponseEntity<Category> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto){
//        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
//    }

    @PatchMapping("/category/{id}")
    private ResponseEntity<CategoryDto> updateCategoryByFields(@PathVariable Long id,@RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(categoryService.updateByField(id,fields));
    }

    @DeleteMapping("/category/{id}")
    private ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.deleteById(id));
    }
}
