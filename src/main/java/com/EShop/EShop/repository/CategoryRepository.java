package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public List<Category> findAllByIsActive(Boolean isActive);
    public Optional<Category> findByName(String name);
    public Optional<Category> findByIdAndIsActive(String name,Boolean isActive);

    //AN UPDATE QUERY TO MAKE CONNECTED PRODUCTS INACTIVE ON CATEGORY DELETE
}
