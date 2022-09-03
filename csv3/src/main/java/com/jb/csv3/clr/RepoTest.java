package com.jb.csv3.clr;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.repository.CompanyRepository;
import com.jb.csv3.repository.CouponRepository;
import com.jb.csv3.repository.CustomerRepository;
import com.jb.csv3.service.CustomerServiceImpl;
import com.jb.csv3.utils.TablePrinter;
import com.jb.csv3.utils.Titles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

import java.util.Arrays;

@Component
@Order(2)
public class RepoTest implements CommandLineRunner {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" ");
        System.out.println(Titles.REPO_TEST);
        System.out.println("   **********          COMPANIES          ********** \n");

        Company company1 = Company.builder()
                .name("Company I")
                .email("company1@mail.com")
                .password("company1234")
                .build();

        Company company2 = Company.builder()
                .name("Company II")
                .email("company2@mail.com")
                .password("company1234")
//                .coupon()
                .build();

        Company company3 = Company.builder()
                .name("Company III")
//                .coupon()
                .email("company3@mail.com")
                .password("company1234")
                .build();

        Company company4 = Company.builder()
                .name("Company IV")
                .email("company4@mail.com")
                .password("company1234")
//                .coupon()
                .build();

        companyRepository.saveAll(Arrays.asList(company1, company2, company3, company4));
        TablePrinter.print(companyRepository.findAll());

        System.out.println("   **********          COUPONS          ********** \n");

        Coupon c1 = Coupon.builder()
//                        .companyId(1)
                .title("Coupon I")
                .description("coupon one description")
                .category(Category.ELECTRONICS)
                .amount(100)
                .price(10.50)
                .startDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 30)))
                .company(companyRepository.findById(1).get())
                .build();

        Coupon c2 = Coupon.builder()
//                        .companyId(2)
                .title("Coupon II")
                .description("coupon 2 description")
                .category(Category.PHARMA)
                .amount(200)
                .price(20.50)
                .startDate(Date.valueOf(LocalDate.of(2022, 10, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 10, 30)))
                .company(companyRepository.findById(2).get())
                .build();

        Coupon c3 = Coupon.builder()
//                        .companyId(3)
                .title("Coupon III")
                .description("coupon 3 description")
                .category(Category.FOOD)
                .amount(200)
                .price(20.50)
                .startDate(Date.valueOf(LocalDate.of(2022, 10, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 10, 30)))
                .company(companyRepository.findById(3).get())
                .build();

        Coupon c4 = Coupon.builder()
//                        .companyId(4)
                .title("Coupon IV")
                .description("coupon 4 description")
                .category(Category.ELECTRONICS)
                .amount(100)
                .price(10.50)
                .startDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 30)))
                .company(companyRepository.findById(4).get())
                .build();

        couponRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
        TablePrinter.print(couponRepository.findAll());

        System.out.println("   **********          GET ALL COUPONS BY COMPANY ID          ********** \n"
        + "\n");
        TablePrinter.print(companyRepository.findById(1));

        System.out.println("   **********          CUSTOMERS          ********** \n");

        Customer customer1 = Customer.builder()
                .firstName("Alex")
                .lastName("Berger")
                .email("alex@mail.com")
                .password("abc123")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Alex2")
                .lastName("Berger2")
                .email("alex2@mail.com")
                .password("abc123")
                .build();

        customerRepository.saveAll(Arrays.asList(customer1, customer2));
        TablePrinter.print(customerRepository.findAll());

    }
}
