package com.jb.csv3.service;

import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.repository.CompanyRepository;
import com.jb.csv3.repository.CouponRepository;
import com.jb.csv3.repository.CustomerRepository;
import com.jb.csv3.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected TokenManager tokenManager;

    public abstract UUID login(String email, String password) throws CouponSystemException;
    public abstract void logout(UUID token);

}
