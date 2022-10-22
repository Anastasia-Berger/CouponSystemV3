package com.jb.csv3.service;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    boolean login(String email, String password) throws CouponSystemException;

    void logout();

//    public CompanyDto updateCompany(int companyID, CompanyDto companyDto) throws CouponSystemException;

    CouponDto addCoupon(int companyID, CouponDto couponDto) throws CouponSystemException;

    CouponDto updateCoupon(int companyID, int couponId, CouponDto CouponDto) throws CouponSystemException;

    void deleteCoupon(int companyID, int couponID) throws CouponSystemException;

    List<CouponDto> getCompanyCoupons(int companyID);

    List<CouponDto> getCompanyCoupons(int companyID, Category category);

    List<CouponDto> getCompanyCoupons(int companyID, double maxPrice);

    CompanyDto getCompanyDetails(int companyID);

    int count();

}
