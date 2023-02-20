package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public List<Customer> findAllByIsActive(Boolean isActive);

    public Optional<Customer> findByEmail(String email);

    public Optional<Customer> findByCellNumber(String cellNumber);

}
