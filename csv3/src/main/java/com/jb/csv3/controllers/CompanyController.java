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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" /*,allowedHeaders = "*"*/)

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

    @PostMapping("{id}/coupons")
    public void addCoupon(@PathVariable int id, @RequestBody Coupon coupon) throws CouponSystemException{
        companyService.addCoupon(id, coupon);
    }

    @PutMapping("{id}/coupons")
    public void updateCoupon(@PathVariable int id,@RequestBody Coupon coupon) throws CouponSystemException{
        companyService.updateCoupon(id, coupon);
    }

    @DeleteMapping("{id}/coupons/{couponID}")
    public void deleteCoupon(@PathVariable int id, @PathVariable int couponID) throws CouponSystemException{
        companyService.deleteCoupon(id, couponID);
    }

    @GetMapping("{id}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable int id){
        return companyService.getCompanyCoupons(id);
    }

    @GetMapping("{id}/coupons/{category}")
    public List<Coupon> getCompanyCoupons(@PathVariable int id, @PathVariable Category category){
        return companyService.getCompanyCoupons(id, category);
    }

    @GetMapping("coupons/{maxPrice}")
    public List<Coupon> getCompanyCoupons(@RequestParam int id, double maxPrice){
        return companyService.getCompanyCoupons(id, maxPrice);
    }

    @GetMapping("{id}/details")
    Company getCompanyDetails(@PathVariable int id){
        return companyService.getCompanyDetails(id);
    }
}
