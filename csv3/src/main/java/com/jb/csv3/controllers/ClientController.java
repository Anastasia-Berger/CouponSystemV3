package com.jb.csv3.controllers;

import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.login.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public abstract class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("login")
    public boolean login(@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        return clientService.login(email, password);
    }
}
