package com.EShop.EShop.repository;

import com.EShop.EShop.domain.Cart;
import com.EShop.EShop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByDeviceAddress(String deviceAddress);
    public Cart findByCustomer(Customer customer);

}
