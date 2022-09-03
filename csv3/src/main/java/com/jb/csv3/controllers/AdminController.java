package com.jb.csv3.controllers;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.login.LoginManager;
import com.jb.csv3.service.AdminService;
import com.jb.csv3.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")

// TODO: 21/08/2022 re-add extends LoginController
public class AdminController{

    @Autowired
    private ClientService clientService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private LoginManager loginManager;

//    @PostMapping("login")
//    public ClientService login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
//            return loginManager.login(email, password, ClientType.ADMINISTRATOR);
//    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @PutMapping("{companyID}")
    public void updateCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(company);
    }

    @DeleteMapping("companies/{companyID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyID) throws CouponSystemException {
        adminService.deleteCompany(companyID);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyID}")
    public Company getOneCompany(@PathVariable int companyID) {
        return adminService.getOneCompany(companyID);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }

    @PutMapping("customers")
    public void updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customer);
    }

    @DeleteMapping("{customerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerID) throws CouponSystemException {
        adminService.deleteCustomer(customerID);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerID}")
    public Customer getOneCustomer(@PathVariable int customerID) {
        return adminService.getOneCustomer(customerID);
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCoupons() {
        return adminService.getAllCoupons();
    }

    @GetMapping("coupons/count")
    public int count() {
        return adminService.count();
    }

}
