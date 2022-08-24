package com.jb.csv3.repository;

import com.jb.csv3.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByTitle(String couponTitle);

    @Query(value = "DELETE FROM csv3.customers_coupons WHERE coupons_id= :couponID", nativeQuery = true)
    void deleteCouponPurchaseHistory(int couponID);
}
