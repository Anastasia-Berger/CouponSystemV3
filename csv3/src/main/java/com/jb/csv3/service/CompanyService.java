package com.jb.csv3.service;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface CompanyService {

    boolean login(String email, String password) throws CouponSystemException;
    void addCoupon(int companyID, Coupon coupon) throws CouponSystemException;
    void updateCoupon(int companyID, Coupon coupon) throws CouponSystemException;
    void deleteCoupon(int companyID, int couponID) throws CouponSystemException;
    List<Coupon> getCompanyCoupons(int companyID);
    List<Coupon> getCompanyCoupons(int companyID, Category category);
    List<Coupon> getCompanyCoupons(int companyID, double maxPrice);
    Company getCompanyDetails(int companyID);

}
