package com.jb.csv3.controllers;

import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.security.TokenManager;
import com.jb.csv3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;
//    private final LoginManager loginManager;
    private final TokenManager tokenManager;

//    @PostMapping("register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void register(@RequestBody RegisterReqDto registerReqDto) throws CouponSystemException {
//        String email = registerReqDto.getEmail();
//        String password = registerReqDto.getPassword();
//        customerService.register(email, password);
//    }
//
//    @PostMapping("login")
//    @ResponseStatus(HttpStatus.CREATED)
//    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
//        String email = loginReqDto.getEmail();
//        String password = loginReqDto.getPassword();
//        UUID uuid = loginManager.login(email, password, ClientType.CUSTOMER);
//        ClientType clientType = tokenManager.getClientType(uuid);
//        return new LoginResDto(email,uuid,clientType);
//    }

    @GetMapping("coupons")
    public List<CouponDto> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        tokenManager.getUserID(token);
        return customerService.getAllCoupons();
    }

    @PostMapping("purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public CouponDto purchaseCoupon(@RequestBody CouponDto couponDto, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int userId = tokenManager.getUserID(token);
        return customerService.purchaseCoupon(userId, couponDto);
    }

    @GetMapping("coupons/purchased")
    public List<CouponDto> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int customerID = tokenManager.getUserID(token);
        return customerService.getCustomerCoupons(customerID);
    }

    @GetMapping("coupons/{category}")
    public List<CouponDto> getCustomerCoupons(@PathVariable Category category, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int userId = tokenManager.getUserID(token);
        return customerService.getCustomerCoupons(userId, category);
    }

    @GetMapping("coupons/{maxPrice}")
    public List<CouponDto> getCustomerCoupons(@PathVariable double maxPrice, @RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int userId = tokenManager.getUserID(token);
        return customerService.getCustomerCoupons(userId, maxPrice);
    }

    @GetMapping("details")
    public CustomerDto getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        int userId = tokenManager.getUserID(token);
        return customerService.getCustomerDetails(userId);
    }
}
