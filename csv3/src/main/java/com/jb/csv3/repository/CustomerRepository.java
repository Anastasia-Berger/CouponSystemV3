package com.jb.csv3.repository;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    Customer findTop1ByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    boolean findByEmailAndPassword(String email, String password);

//    @Query(value = "SELECT coupons_id FROM customers_coupons where customer_id = :customerID;", nativeQuery = true)
//    List<Coupon> findByCustomerId(@Param("customerId") int customerId);

}
