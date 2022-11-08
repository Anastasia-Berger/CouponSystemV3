package com.jb.csv3.service;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;
import java.util.Set;

public interface AdminService {

    // Authorization

    boolean login(String email, String password) throws CouponSystemException;

    void logout();

    // Companies

    CompanyDto addCompany(CompanyDto companyDto) throws CouponSystemException;

    CompanyDto updateCompany(int companyID, CompanyDto CompanyDto) throws CouponSystemException;

    void deleteCompany(int companyID) throws CouponSystemException;

    List<CompanyDto> getAllCompanies();

    CompanyDto getOneCompany(int companyID) throws CouponSystemException;

    List<CouponDto> getCompanyCoupons(int companyID);

    int countCompanies();

    // Customers

    CustomerDto addCustomer(CustomerDto customerDto) throws CouponSystemException;

    CustomerDto updateCustomer(int customerID, CustomerDto customerDto) throws CouponSystemException;

    void deleteCustomer(int customerID) throws CouponSystemException;

    List<CustomerDto> getAllCustomers();

    CustomerDto getOneCustomer(int customerID);

    Set<CouponDto> getCustomerCoupons(int customerID);

    int countCustomers();

    // All Coupons

    List<CouponDto> getAllCoupons();

    int countCoupons();
}
