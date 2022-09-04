package com.jb.csv3.controllers;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CompanyController{

    private final CompanyService companyService;

    @PostMapping("{companyID}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public CouponDto addCoupon(@PathVariable int companyID, @RequestBody CouponDto couponDto) throws CouponSystemException{
       return companyService.addCoupon(companyID, couponDto);
    }

    @PutMapping("{companyID}/coupons")
    public CouponDto updateCoupon(@PathVariable int companyID,@RequestBody CouponDto couponDto) throws CouponSystemException{
        return companyService.updateCoupon(companyID, couponDto);
    }

    @DeleteMapping("{companyID}/coupons/{couponID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int companyID, @PathVariable int couponID) throws CouponSystemException{
        companyService.deleteCoupon(companyID, couponID);
    }

    @GetMapping("{companyID}/coupons")
    public List<CouponDto> getCompanyCoupons(@PathVariable int companyID){
        return companyService.getCompanyCoupons(companyID);
    }

    @GetMapping("{companyID}/coupons/{category}")
    public List<CouponDto> getCompanyCoupons(@PathVariable int companyID, @PathVariable Category category){
        return companyService.getCompanyCoupons(companyID, category);
    }

    @GetMapping("coupons/{maxPrice}")
    public List<CouponDto> getCompanyCoupons(@RequestParam int companyID, double maxPrice){
        return companyService.getCompanyCoupons(companyID, maxPrice);
    }

    @GetMapping("{companyID}/details")
    CompanyDto getCompanyDetails(@PathVariable int companyID){
        return companyService.getCompanyDetails(companyID);
    }

    @GetMapping("/count")
    public int count() {
        return companyService.count();
    }
}
