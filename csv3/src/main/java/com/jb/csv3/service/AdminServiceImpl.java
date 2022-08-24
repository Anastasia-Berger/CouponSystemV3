package com.jb.csv3.service;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
import com.jb.csv3.entity.Customer;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!email.equals("admin@admin.com") || !password.equals("admin"))
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);

        return true;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        // Check name
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.DUPLICATE_COMPANY_NAME);
        }

        // Check e-mail
        if (companyRepository.existsByEmail((company.getEmail()))) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }

        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        // Checking if the company is exists for update
        if (!companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyID) throws CouponSystemException {
        // Checking if the company is exists for update
        if (!companyRepository.existsById(companyID)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        companyRepository.deleteById(companyID);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int companyID) {
        return companyRepository.findById(companyID).get();
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        // Check e-mail
        if (customerRepository.existsByEmail((customer.getEmail()))) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }

        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        // Checking if customer is exists
        if (!customerRepository.existsById(customer.getId())) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        // Checking if customer is exists
        if (!customerRepository.existsById(customerID)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        customerRepository.deleteById(customerID);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return customerRepository.findById(customerID).get();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
