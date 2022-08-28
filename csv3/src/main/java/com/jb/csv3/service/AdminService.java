package com.jb.csv3.service;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;

import java.util.List;

public interface AdminService{
    boolean login(String email, String password) throws CouponSystemException;
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(int companyID) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID);
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerID) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID);
    List<Coupon> getAllCoupons();
    int count();

}
