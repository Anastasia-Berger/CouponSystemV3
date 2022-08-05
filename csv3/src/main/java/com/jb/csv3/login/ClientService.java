package com.jb.csv3.login;

import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.repository.CompanyRepository;
import com.jb.csv3.repository.CouponRepository;
import com.jb.csv3.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    //    public abstract UUID login(String email, String password) throws CouponSystemException;
    public abstract boolean login(String email, String password) throws CouponSystemException;


}
