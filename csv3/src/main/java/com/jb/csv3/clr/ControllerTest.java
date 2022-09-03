package com.jb.csv3.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(4)
public class ControllerTest implements CommandLineRunner {

    // TODO: 17/08/2022 : controllers test
    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/admin/companies";

    @Override
    public void run(String... args) throws Exception {

//        restTemplate.getForObject(URL, Company[].class);

    }
}
