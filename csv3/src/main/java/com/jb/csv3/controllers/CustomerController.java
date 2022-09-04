package com.jb.csv3.controllers;

import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("{customerID}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    CouponDto purchaseCoupon(@PathVariable int customerID, @RequestBody CouponDto couponDto) throws CouponSystemException {
        return customerService.purchaseCoupon(customerID, couponDto);
    }

    @GetMapping("{customerID}/coupons")
    public List<CouponDto> getCustomerCoupons(@PathVariable int customerID) {
        return customerService.getCustomerCoupons(customerID);
    }

    @GetMapping("{customerID}/coupons/{category}")
    public List<CouponDto> getCustomerCoupons(@PathVariable int customerID, @PathVariable Category category) {
        return customerService.getCustomerCoupons(customerID, category);
    }

    @GetMapping("{customerID}/coupons/{maxPrice}")
    public List<CouponDto> getCustomerCoupons(@PathVariable int customerID, @PathVariable double maxPrice) {
        return customerService.getCustomerCoupons(customerID, maxPrice);
    }

    @GetMapping("{customerID}/details")
    public CustomerDto getCustomerDetails(@PathVariable int customerID) {
        return customerService.getCustomerDetails(customerID);
    }
}
