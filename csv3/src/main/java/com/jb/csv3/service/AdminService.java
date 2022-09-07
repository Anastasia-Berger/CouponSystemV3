package com.jb.csv3.service;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface AdminService {

    boolean login(String email, String password) throws CouponSystemException;

    void logout();

    CompanyDto addCompany(CompanyDto companyDto) throws CouponSystemException;

    CompanyDto updateCompany(int companyID, CompanyDto CompanyDto) throws CouponSystemException;

    void deleteCompany(int companyID) throws CouponSystemException;

    List<CompanyDto> getAllCompanies();

    CompanyDto getOneCompany(int companyID) throws CouponSystemException;

    CustomerDto addCustomer(CustomerDto customerDto) throws CouponSystemException;

    CustomerDto updateCustomer(int customerID, CustomerDto customerDto) throws CouponSystemException;

    void deleteCustomer(int customerID) throws CouponSystemException;

    List<CustomerDto> getAllCustomers();

    CustomerDto getOneCustomer(int customerID);

    List<CouponDto> getAllCoupons();

    int countCompanies();

    int countCustomers();

    int countCoupons();
}
