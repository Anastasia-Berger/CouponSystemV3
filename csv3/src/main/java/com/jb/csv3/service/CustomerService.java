package com.jb.csv3.service;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface CustomerService {

    boolean login(String email, String password) throws CouponSystemException;
    void purchaseCoupon(int customerID, Coupon coupon) throws CouponSystemException;
    List<Coupon> getCustomerCoupons(int customerID);
    List<Coupon> getCustomerCoupons(int customerID, Category category);
    List<Coupon> getCustomerCoupons(int customerID, double maxPrice);
    Customer getCustomerDetails(int customerID);

}
