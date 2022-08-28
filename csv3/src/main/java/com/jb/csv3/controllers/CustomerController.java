package com.jb.csv3.controllers;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.login.LoginManager;
import com.jb.csv3.service.ClientService;
import com.jb.csv3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" /*,allowedHeaders = "*"*/)

// TODO: 21/08/2022 re-add extends LoginController
public class CustomerController{

    @Autowired
    private ClientService clientService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginManager loginManager;

//    @PostMapping("login")
//    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        return new ResponseEntity<>(loginManager.login(email, password, ClientType.CUSTOMER), HttpStatus.OK);
//    }

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
