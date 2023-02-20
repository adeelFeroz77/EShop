package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Category;
import com.EShop.EShop.domain.Model;
import com.EShop.EShop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findAllByIsActive(Boolean isActive);
    public Optional<Product> findByNameAndCategoryAndModel(String name, Category category, Model model);
    public Optional<Product> findByIdAndIsActive(Long id, Boolean isActive);
}
