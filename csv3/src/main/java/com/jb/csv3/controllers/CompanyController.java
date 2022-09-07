package com.jb.csv3.controllers;

import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.security.LoginManager;
import com.jb.csv3.security.TokenManager;
import com.jb.csv3.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompanyController{

    private final CompanyService companyService;
    private final LoginManager loginManager;
    private final TokenManager tokenManager;

//    @PostMapping("login")
//    @ResponseStatus(HttpStatus.CREATED)
//    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
//        String email = loginReqDto.getEmail();
//        String password = loginReqDto.getPassword();
//        UUID uuid = loginManager.login(email, password, ClientType.COMPANY);
//        ClientType clientType = tokenManager.getClientType(uuid);
//        return new LoginResDto(email,uuid,clientType);
//    }

    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public CouponDto addCoupon(@RequestBody CouponDto couponDto, @RequestHeader("Authorization") UUID token) throws CouponSystemException{
        int companyID = tokenManager.getUserID(token);
        return companyService.addCoupon(companyID, couponDto);
    }

    @PutMapping("coupons")
    public CouponDto updateCoupon(@RequestBody CouponDto couponDto, @RequestHeader("Authorization") UUID token) throws CouponSystemException{
        int companyID = tokenManager.getUserID(token);
        return companyService.updateCoupon(companyID, couponDto);
    }

    @DeleteMapping("{coupons/{couponID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponID, @RequestHeader("Authorization") UUID token) throws CouponSystemException{
        int companyID = tokenManager.getUserID(token);
        companyService.deleteCoupon(companyID, couponID);
    }

    @GetMapping("coupons")
    public List<CouponDto> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int companyID = tokenManager.getUserID(token);
        return companyService.getCompanyCoupons(companyID);
    }

    @GetMapping("coupons/{category}")
    public List<CouponDto> getCompanyCoupons(@PathVariable Category category, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int companyID = tokenManager.getUserID(token);
        return companyService.getCompanyCoupons(companyID, category);
    }

    @GetMapping("coupons/{maxPrice}")
    public List<CouponDto> getCompanyCoupons(@PathVariable double maxPrice, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int companyID = tokenManager.getUserID(token);
        return companyService.getCompanyCoupons(companyID, maxPrice);
    }

    @GetMapping("details")
    CompanyDto getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int companyID = tokenManager.getUserID(token);
        return companyService.getCompanyDetails(companyID);
    }
//
//    @GetMapping("count")
//    public int count(@RequestHeader("Authorization") UUID token) {
//        return companyService.count();
//    }
}
