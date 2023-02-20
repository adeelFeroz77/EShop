package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public Transaction findTransactionById(Long id);

    @Query(value = "Select SUM(quantity*price) from transaction t inner join product_cart pc on t.cart_id=pc.cart_id where t.cart_id = :id",nativeQuery = true)
    public Double getTotalAmount(@Param("id") Long id);
}
