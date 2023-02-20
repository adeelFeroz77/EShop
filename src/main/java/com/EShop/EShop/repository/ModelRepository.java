package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
    public List<Model> findAllByIsActive(Boolean isActive);
    public Optional<Model> findByName(String name);
    public Optional<Model> findByIdAndIsActive(String name,Boolean isActive);

    //AN UPDATE QUERY TO MAKE CONNECTED PRODUCTS INACTIVE ON CATEGORY DELETE
}
