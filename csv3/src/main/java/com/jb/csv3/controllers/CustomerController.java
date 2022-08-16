package com.jb.csv3.controllers;

import com.jb.csv3.entity.Coupon;
import com.jb.csv3.entity.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController extends ClientController{

    @Autowired
    private CustomerService customerService;

    @PostMapping("login")
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return customerService.login(email, password);
    }

    @PostMapping("{id}/coupons")
    void purchaseCoupon(@PathVariable int id, @RequestBody Coupon coupon) throws CouponSystemException{
        customerService.purchaseCoupon(id, coupon);
    }

    @GetMapping("{id}/coupons")
    public List<Coupon> getCustomerCoupons(@PathVariable int id){
        return customerService.getCustomerCoupons(id);
    }

    @GetMapping("{id}/coupons/{category}")
    public List<Coupon> getCustomerCoupons(@PathVariable int id, @PathVariable Category category){
        return customerService.getCustomerCoupons(id, category);
    }

    @GetMapping("{id}/coupons/{maxPrice}")
    public List<Coupon> getCustomerCoupons(@PathVariable int id, @PathVariable double maxPrice){
        return customerService.getCustomerCoupons(id, maxPrice);
    }

    @GetMapping("{id}/details")
    public Customer getCustomerDetails(@PathVariable int id){
        return customerService.getCustomerDetails(id);
    }
}
