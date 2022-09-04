package com.jb.csv3.service;

import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    void register(String email, String password) throws CouponSystemException;
    UUID login(String email, String password) throws CouponSystemException;
    void logout();

    CouponDto purchaseCoupon(int customerID, CouponDto CouponDto) throws CouponSystemException;
    List<CouponDto> getCustomerCoupons(int customerID);
    List<CouponDto> getCustomerCoupons(int customerID, Category category);
    List<CouponDto> getCustomerCoupons(int customerID, double maxPrice);
    CustomerDto getCustomerDetails(int customerID);

}
