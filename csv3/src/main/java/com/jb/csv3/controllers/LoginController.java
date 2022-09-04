package com.jb.csv3.controllers;

import com.jb.csv3.dto.loginDto.LoginReqDto;
import com.jb.csv3.dto.loginDto.LoginResDto;
import com.jb.csv3.dto.loginDto.RegisterReqDto;
import com.jb.csv3.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.security.TokenManager;
import com.jb.csv3.service.ClientService;
import com.jb.csv3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {

    private  final ClientService clientService;
    private final CustomerService customerService;
    private final TokenManager tokenManager;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterReqDto registerReqDto) throws CouponSystemException {
        String email = registerReqDto.getEmail();
        String password = registerReqDto.getPassword();
        customerService.register(email, password);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        UUID uuid = clientService.login(email, password);
        ClientType clientType = tokenManager.getClientType(uuid);
        return new LoginResDto(email,uuid,clientType);
    }

    @PutMapping("logout/{uuid}")
    public void logout(@RequestParam UUID token) {
        clientService.logOut(token);
    }

}