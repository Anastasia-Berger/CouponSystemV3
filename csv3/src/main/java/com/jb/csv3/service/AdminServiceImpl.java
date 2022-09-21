package com.jb.csv3.service;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.mappers.CompanyMapper;
import com.jb.csv3.mappers.CouponMapper;
import com.jb.csv3.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {

    private final CompanyMapper companyMapper;
    private final CustomerMapper customerMapper;
    private final CouponMapper couponMapper;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!email.equals("admin@admin.com") && !password.equals("admin")) {
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }
        return true;
    }

    @Override
    public void logout() {
    }

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) throws CouponSystemException {
        Company company = companyMapper.toDAO(companyDto);

        // Check name
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.DUPLICATE_COMPANY_NAME);
        }

        // Check e-mail
        if (companyRepository.existsByEmail((company.getEmail()))) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }

        return companyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDto updateCompany(int companyID, CompanyDto companyDto) throws CouponSystemException {
        // Checking if company for update exists in repository
        if (!companyRepository.existsById(companyID)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }

        companyDto.setId(companyID);
        Company company = companyMapper.toDAO(companyDto);

        return companyMapper.toDTO(companyRepository.saveAndFlush(company));
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
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.toDtoList(companyRepository.findAll());
    }

    @Override
    public CompanyDto getOneCompany(int companyID) throws CouponSystemException {
        Company company = companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemException(ErrMsg.ID_NOT_EXIST));
        return companyMapper.toDTO(company);
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) throws CouponSystemException {
        Customer customer = customerMapper.toDAO(customerDto);
        // Check e-mail
        if (customerRepository.existsByEmail((customer.getEmail()))) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }

        return customerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDto updateCustomer(int customerID, CustomerDto customerDto) throws CouponSystemException {
        // Checking if customer is exists
        if (!customerRepository.existsById(customerID)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }

        customerDto.setId(customerID);
        Customer customer = customerMapper.toDAO(customerDto);

        return customerMapper.toDTO(customerRepository.saveAndFlush(customer));
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
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto getOneCustomer(int customerID) {
        return customerMapper.toDTO(customerRepository.findById(customerID).get());
    }

    @Override
    public List<CouponDto> getCustomerCoupons(int customerID) {
        return couponMapper.toDtoList(customerRepository.findById(customerID).get().getCoupons());
    }

    @Override
    public List<CouponDto> getAllCoupons() {
        return couponMapper.toDtoList(couponRepository.findAll());
    }

    @Override
    public int countCompanies() {
        return (int) companyRepository.count();
    }

    @Override
    public int countCustomers() {
        return (int) customerRepository.count();
    }

    @Override
    public int countCoupons() {
        return (int) couponRepository.count();
    }


}
