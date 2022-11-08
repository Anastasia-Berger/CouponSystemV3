package com.jb.csv3.service;

import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    void register(String firstName, String lastName, String email, String password) throws CouponSystemException;

    boolean login(String email, String password) throws CouponSystemException;

    void logout();

    List<CouponDto> getAllCoupons();

    CouponDto purchaseCoupon(int customerID, int couponId/*, CouponDto CouponDto*/) throws CouponSystemException;

    Set<CouponDto> getCustomerCoupons(int customerID);

    Set<CouponDto> getCustomerCoupons(int customerID, Category category);

    Set<CouponDto> getCustomerCoupons(int customerID, double maxPrice);

    CustomerDto getCustomerDetails(int customerID) throws CouponSystemException;

    int count();
}
