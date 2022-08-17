package com.jb.csv3.controllers;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Customer;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.login.ClientService;
import com.jb.csv3.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController extends ClientController{

    @Autowired
    private ClientService clientService;
    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return clientService.login(email, password);
    }

    @PostMapping("companies")
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @PutMapping("companies")
    public void updateCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(company);
    }

    @DeleteMapping("companies/{companyID}")
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
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }

    @PutMapping("customers")
    public void updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customer);
    }

    @DeleteMapping("customers")
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

}
