package com.jb.csv3.controllers;

import com.jb.csv3.dto.loginDto.LoginReqDto;
import com.jb.csv3.dto.loginDto.LoginResDto;
import com.jb.csv3.dto.loginDto.RegisterReqDto;
import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.security.LoginManager;
import com.jb.csv3.security.TokenManager;
import com.jb.csv3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*" /*, allowedHeaders = "*"*/)
public class LoginController {

    private final CustomerService customerService;
    private final LoginManager loginManager;
//    private final TokenManager tokenManager;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterReqDto registerReqDto) throws CouponSystemException {
        String firstName = registerReqDto.getEmail();
        String lastName = registerReqDto.getPassword();
        String email = registerReqDto.getEmail();
        String password = registerReqDto.getPassword();
        customerService.register(firstName, lastName, email, password);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        ClientType type = loginReqDto.getClientType();

        UUID uuid = loginManager.login(email, password, type);
//        ClientType clientType = tokenManager.getClientType(uuid);

        return new LoginResDto(email, uuid, type);
    }

}
