package com.jb.csv3.service;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon) throws CouponSystemException;
    void updateCoupon(Coupon coupon) throws CouponSystemException;
    void deleteCoupon(int couponID) throws CouponSystemException;
    List<Coupon> getCompanyCoupons();
    List<Coupon> getCompanyCoupons(Category category);
    List<Coupon> getCompanyCoupons(double maxPrice);
    Company getCompanyDetails();

}
