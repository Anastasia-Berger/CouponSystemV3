package com.jb.csv3.controllers;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.security.TokenManager;
import com.jb.csv3.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AdminController {

    private final AdminService adminService;
//    private final LoginManager loginManager;
    private final TokenManager tokenManager;

//    @PostMapping("login")
//    @ResponseStatus(HttpStatus.CREATED)
//    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
//        String email = loginReqDto.getEmail();
//        String password = loginReqDto.getPassword();
//        UUID uuid = loginManager.login(email, password, ClientType.ADMINISTRATOR);
//        ClientType clientType = tokenManager.getClientType(uuid);
//        return new LoginResDto(email,uuid,clientType);
//    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto addCompany(@RequestBody CompanyDto companyDto, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.addCompany(companyDto);
    }

    @PutMapping("companies/{companyID}")
    public CompanyDto updateCompany(@PathVariable int companyID, @RequestBody CompanyDto companyDto, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.updateCompany(companyID, companyDto);
    }

    @DeleteMapping("companies/{companyID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyID, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        adminService.deleteCompany(companyID);
    }

    @GetMapping("companies")
    public List<CompanyDto> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyID}")
    public CompanyDto getOneCompany(@PathVariable int companyID, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getOneCompany(companyID);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.addCustomer(customerDto);
    }

    @PutMapping("customers/{customerID}")
    public CustomerDto updateCustomer(
            @RequestBody CustomerDto customerDto,
            @PathVariable int customerID,
            @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.updateCustomer(customerID, customerDto);
    }

    @DeleteMapping("customers/{customerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerID, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        adminService.deleteCustomer(customerID);
    }

    @GetMapping("customers")
    public List<CustomerDto> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerID}")
    public CustomerDto getOneCustomer(@PathVariable int customerID, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getOneCustomer(customerID);
    }

//    GET COUPONS
    @GetMapping("coupons")
    public List<CouponDto> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getAllCoupons();
    }

    @GetMapping("customers/{customerID}/coupons")
    public Set<CouponDto> getCustomerCoupons(@PathVariable int customerID, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getCustomerCoupons(customerID);
    }

    @GetMapping("companies/count")
    public int countCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.countCompanies();
    }

    @GetMapping("customers/count")
    public int countCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.countCustomers();
    }

    @GetMapping("coupons/count")
    public int countCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.countCoupons();
    }

    @GetMapping("companies/coupons/count/{companyId}")
    public int countCompanyCoupons(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getCompanyCoupons(companyId).size();
    }

    @GetMapping("customer/coupons/count/{customerId}")
    public int countCustomerCoupons(@PathVariable int customerID, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.isAdmin(token);
        return adminService.getCustomerCoupons(customerID).size();
    }

}
