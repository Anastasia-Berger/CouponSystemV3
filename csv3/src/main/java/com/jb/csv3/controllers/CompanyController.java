package com.jb.csv3.controllers;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("companies")
public class CompanyController extends ClientController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("login")
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return companyService.login(email, password);
    }

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
