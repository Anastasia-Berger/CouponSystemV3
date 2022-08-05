package com.jb.csv3.service;

import com.jb.csv3.entity.Coupon;
import com.jb.csv3.entity.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface CustomerService {

    void purchaseCoupon(Coupon coupon) throws CouponSystemException;
    List<Coupon> getCustomerCoupons();
    List<Coupon> getCustomerCoupons(Category category);
    List<Coupon> getCustomerCoupons(double maxPrice);
    Customer getCustomerDetails();

}
