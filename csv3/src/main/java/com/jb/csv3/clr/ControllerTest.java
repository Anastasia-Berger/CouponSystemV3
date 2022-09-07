package com.jb.csv3.clr;

import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.repository.CompanyRepository;
import com.jb.csv3.repository.CouponRepository;
import com.jb.csv3.repository.CustomerRepository;
import com.jb.csv3.security.LoginManager;
import com.jb.csv3.service.AdminService;
import com.jb.csv3.utils.Titles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@Order(4)
@RequiredArgsConstructor
public class ControllerTest implements CommandLineRunner {

    @Autowired
    public LoginManager loginManager;

    private static final String URL = "http://localhost:9090/";

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Titles.CONTROLLER_TEST);

//        System.out.println("   **********          ADMIN UUID LOGIN          ********** \n");
//        UUID admin = loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//        System.out.println(admin.toString());
//
//        System.out.println("   **********          COMPANY UUID LOGIN          ********** \n");
//        UUID company = loginManager.login("pizza-hut@mail.com", "company1234", ClientType.CUSTOMER);
//        System.out.println(company.toString());
//
//        System.out.println("   **********          CUSTOMER UUID LOGIN          ********** \n");
//        UUID customer = loginManager.login("alex@mail.com", "customer1234", ClientType.CUSTOMER);
//        System.out.println(customer.toString());

    }
}
