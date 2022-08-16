package com.jb.csv3.clr;

import com.jb.csv3.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(3)
public class ControllerTest implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/admin/companies";

    @Override
    public void run(String... args) throws Exception {

//        restTemplate.getForObject(URL, Company[].class);
    }
}
