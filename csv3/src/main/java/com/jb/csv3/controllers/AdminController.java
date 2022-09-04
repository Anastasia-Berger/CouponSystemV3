package com.jb.csv3.controllers;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AdminController {

    private final AdminService adminService;

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto addCompany(@RequestBody CompanyDto companyDto) throws CouponSystemException {
        return adminService.addCompany(companyDto);
    }

    @PutMapping("{companyID}")
    public CompanyDto updateCompany(@PathVariable int companyID, @RequestBody CompanyDto companyDto) throws CouponSystemException {
        return adminService.updateCompany(companyID, companyDto);
    }

    @DeleteMapping("companies/{companyID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyID) throws CouponSystemException {
        adminService.deleteCompany(companyID);
    }

    @GetMapping("companies")
    public List<CompanyDto> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyID}")
    public CompanyDto getOneCompany(@PathVariable int companyID) throws CouponSystemException {
        return adminService.getOneCompany(companyID);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) throws CouponSystemException {
        return adminService.addCustomer(customerDto);
    }

    @PutMapping("customers/{customerID}")
    public CustomerDto updateCustomer(@PathVariable int customerID, @RequestBody CustomerDto customerDto) throws CouponSystemException {
        return adminService.updateCustomer(customerID, customerDto);
    }

    @DeleteMapping("{customerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerID) throws CouponSystemException {
        adminService.deleteCustomer(customerID);
    }

    @GetMapping("customers")
    public List<CustomerDto> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerID}")
    public CustomerDto getOneCustomer(@PathVariable int customerID) {
        return adminService.getOneCustomer(customerID);
    }

    @GetMapping("coupons")
    public List<CouponDto> getAllCoupons() {
        return adminService.getAllCoupons();
    }

    @GetMapping("coupons/count")
    public int count() {
        return adminService.count();
    }

}
