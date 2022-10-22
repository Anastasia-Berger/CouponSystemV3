package com.jb.csv3.repository;

import com.jb.csv3.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByTitle(String couponTitle);

    @Modifying
    @Query(value = "DELETE FROM customers_coupons WHERE coupons_id= :couponID", nativeQuery = true)
    void deleteCouponPurchaseHistory(int couponID);

    List<Coupon> findByCompanyId(int companyID);

//    @Query(value = "SELECT * FROM customers_coupons WHERE `CUSTOMER_ID` = :customerId", nativeQuery = true)
//    List<Coupon> findByCustomerId(@Param("customerId") int customerId);

    @Override
    List<Coupon> findAll();

    @Query(value = "select exists (SELECT * FROM customers_coupons WHERE `CUSTOMER_ID` = :customerId and `coupons_id` = :couponId) as res", nativeQuery = true)
    int existsByCustomerIdAndCouponId(@Param("customerId") int customerId, @Param("couponId") int couponId);
}
