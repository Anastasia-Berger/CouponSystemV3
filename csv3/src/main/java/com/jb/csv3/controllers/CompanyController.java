package com.jb.csv3.controllers;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.login.LoginManager;
import com.jb.csv3.service.ClientService;
import com.jb.csv3.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")

// TODO: 21/08/2022 re-add extends LoginController
public class CompanyController{

    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LoginManager loginManager;

//    @PostMapping("login")
//    public ClientService login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        return loginManager.login(email, password, ClientType.COMPANY);
//    }

    @PostMapping("{companyID}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyID, @RequestBody Coupon coupon) throws CouponSystemException{
        companyService.addCoupon(companyID, coupon);
    }

    @PutMapping("{companyID}/coupons")
    public void updateCoupon(@PathVariable int companyID,@RequestBody Coupon coupon) throws CouponSystemException{
        companyService.updateCoupon(companyID, coupon);
    }

    @DeleteMapping("{companyID}/coupons/{couponID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int companyID, @PathVariable int couponID) throws CouponSystemException{
        companyService.deleteCoupon(companyID, couponID);
    }

    @GetMapping("{companyID}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyID){
        return companyService.getCompanyCoupons(companyID);
    }

    @GetMapping("{companyID}/coupons/{category}")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyID, @PathVariable Category category){
        return companyService.getCompanyCoupons(companyID, category);
    }

    @GetMapping("coupons/{maxPrice}")
    public List<Coupon> getCompanyCoupons(@RequestParam int companyID, double maxPrice){
        return companyService.getCompanyCoupons(companyID, maxPrice);
    }

    @GetMapping("{companyID}/details")
    Company getCompanyDetails(@PathVariable int companyID){
        return companyService.getCompanyDetails(companyID);
    }
}
