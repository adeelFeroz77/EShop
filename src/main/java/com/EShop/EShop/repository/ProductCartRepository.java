package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Cart;
import com.EShop.EShop.domain.Product;
import com.EShop.EShop.domain.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
    public List<ProductCart> findAllByCartId(Long id);

    @Query("From ProductCart where cart.id = :id")
    public List<ProductCart> getAllByCartId(Long id);

    public List<ProductCart> findAllByProduct(Product product);

    public Optional<ProductCart> findByCartAndProduct(Cart cart, Product product);

    public void deleteByCartAndProduct(Cart cart, Product product);
}
