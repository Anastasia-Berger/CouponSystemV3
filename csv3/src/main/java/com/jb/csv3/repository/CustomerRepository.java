package com.jb.csv3.repository;

import com.jb.csv3.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean findByEmailAndPassword(String email, String password);

    Customer findByEmail(String email);
}
