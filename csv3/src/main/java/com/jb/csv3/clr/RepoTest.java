package com.jb.csv3.clr;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.repository.CompanyRepository;
import com.jb.csv3.repository.CouponRepository;
import com.jb.csv3.repository.CustomerRepository;
import com.jb.csv3.utils.TablePrinter;
import com.jb.csv3.utils.Titles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Order(2)
public class RepoTest implements CommandLineRunner {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" ");
        System.out.println(Titles.REPO_TEST);
        System.out.println("   **********          COMPANIES          ********** \n");

        Company company1 = Company.builder()
                .name("Shookit")
                .email("shookit@mail.com")
                .password("company1234")
                .build();

        Company company2 = Company.builder()
                .name("Tari Al Aboker")
                .email("boker@mail.com")
                .password("company1234")
                .build();

        Company company3 = Company.builder()
                .name("Pizza Hut")
                .email("pizza-hut@mail.com")
                .password("company1234")
                .build();

        Company company4 = Company.builder()
                .name("Papa Johns")
                .email("papa-johns@mail.com")
                .password("company1234")
                .build();

        Company company5 = Company.builder()
                .name("EL AL")
                .email("elal@mail.com")
                .password("company1234")
                .build();

        Company company6 = Company.builder()
                .name("Booking")
                .email("booking@mail.com")
                .password("company1234")
                .build();

        Company company7 = Company.builder()
                .name("SHEIN")
                .email("shein@mail.com")
                .password("company1234")
                .build();

        Company company8 = Company.builder()
                .name("Castro")
                .email("castro@mail.com")
                .password("company1234")
                .build();

        Company company9 = Company.builder()
                .name("!FUNKO POP")
                .email("funko@mail.com")
                .password("company1234")
                .build();

        Company company10 = Company.builder()
                .name("ToysRus")
                .email("toysrus@mail.com")
                .password("company1234")
                .build();

        Company company11 = Company.builder()
                .name("BE")
                .email("be@mail.com")
                .password("company1234")
                .build();

        Company company12 = Company.builder()
                .name("Super Pharm")
                .email("superpharm@mail.com")
                .password("company1234")
                .build();

        Company company13 = Company.builder()
                .name("NIKE")
                .email("nike@mail.com")
                .password("company1234")
                .build();

        Company company14 = Company.builder()
                .name("ADIDAS")
                .email("adidas@mail.com")
                .password("company1234")
                .build();

        Company company15 = Company.builder()
                .name("Apple")
                .email("apple@mail.com")
                .password("company1234")
                .build();

        Company company16 = Company.builder()
                .name("iDigital")
                .email("idigital@mail.com")
                .password("company1234")
                .build();

        Company company17 = Company.builder()
                .name("PC GAMER")
                .email("pcgamer@mail.com")
                .password("company1234")
                .build();

        Company company18 = Company.builder()
                .name("Minecraft")
                .email("minecraft@mail.com")
                .password("company1234")
                .build();

        companyRepository.saveAll(Arrays.asList(
                company1, company2, company3, company4, company5, company6,
                company7, company8, company9, company10, company11, company12,
                company13, company14, company15, company16, company17, company18
        ));

        TablePrinter.print(companyRepository.findAll());

        System.out.println("   **********          COUPONS          ********** \n");

        Coupon c1 = Coupon.builder()
                .company(company1)
                .title("Blueberries")
                .description("2 cases of fresh blueberries")
                .category(Category.FOOD)
                .amount(100)
                .price(25)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c2 = Coupon.builder()
                .company(company1)
                .title("Figs")
                .description("700 gr of Brazilian figs")
                .category(Category.FOOD)
                .amount(100)
                .price(25.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c3 = Coupon.builder()
                .company(company2)
                .title("A round bun")
                .description("1 fresh round bun")
                .category(Category.FOOD)
                .amount(200)
                .price(2.3)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c4 = Coupon.builder()
                .company(company2)
                .title("Lahuh")
                .description("A case of 4 pcs")
                .category(Category.FOOD)
                .amount(200)
                .price(21.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c5 = Coupon.builder()
                .company(company3)
                .title("Truffle cream pizza")
                .description("Truffle cream pizza (our white pizza), price. Thin crust pizza with truffle cream, mozzarella, fresh mushrooms and pineapple. Click here to add to cart.")
                .category(Category.RESTAURANT)
                .amount(200)
                .price(79.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c6 = Coupon.builder()
                .company(company4)
                .title("Family pizza")
                .description("Second family pizza at 50%")
                .category(Category.RESTAURANT)
                .amount(100)
                .price(59.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c7 = Coupon.builder()
                .company(company5)
                .title("Roma")
                .description("15.09.2022-25.09.2022")
                .category(Category.VACATION)
                .amount(20)
                .price(1510.29)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c8 = Coupon.builder()
                .company(company6)
                .title("Athens Quality Apartments")
                .description("Entire apartment, 1 bedroom, 1 living room, 1 bathroom, 1 kitchen, 47m², 2 beds (1 sofa bed, 1 large double), Free cancellation")
                .category(Category.VACATION)
                .amount(20)
                .price(3805)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c9 = Coupon.builder()
                .company(company7)
                .title("20% OFF")
                .description("Buy 3 and get 20% discount")
                .category(Category.CLOTHING)
                .amount(1000)
                .price(10)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c10 = Coupon.builder()
                .company(company8)
                .title("FREE DELIVERY")
                .description("Buy for 200$ and get free shipping")
                .category(Category.CLOTHING)
                .amount(999)
                .price(200)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c11 = Coupon.builder()
                .company(company9)
                .title("Disney: Lilo & Stitch - Vinyl Figure")
                .description("Up to 17 % off – See low price in cart")
                .category(Category.TOYS)
                .amount(999)
                .price(11.99)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c12 = Coupon.builder()
                .company(company10)
                .title("Lego Jurassic World")
                .description("Up to 25% off")
                .category(Category.TOYS)
                .amount(999)
                .price(374.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c13 = Coupon.builder()
                .company(company11)
                .title("CeraVe")
                .description("Up to 20% off")
                .category(Category.PHARMA)
                .amount(999)
                .price(374.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c14 = Coupon.builder()
                .company(company12)
                .title("KIEHL'S")
                .description("1+1")
                .category(Category.PHARMA)
                .amount(10000)
                .price(50)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c15 = Coupon.builder()
                .company(company13)
                .title("Nike Dri-FIT Academy")
                .description("Older Kids' Knit Football Tracksuit")
                .category(Category.SPORTS)
                .amount(100)
                .price(269)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c16 = Coupon.builder()
                .company(company14)
                .title("ADICOLOR ESSENTIALS")
                .description("Brass sweatshirt")
                .category(Category.SPORTS)
                .amount(100)
                .price(229.9)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c17 = Coupon.builder()
                .company(company15)
                .title("iPad Air")
                .description("Apple M1 chip powers next-level performance and all-day battery life")
                .category(Category.ELECTRONICS)
                .amount(100)
                .price(599)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c18 = Coupon.builder()
                .company(company16)
                .title("iWalk ")
                .description("Portable battery 3300mAh - black")
                .category(Category.ELECTRONICS)
                .amount(100)
                .price(149)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c19 = Coupon.builder()
                .company(company18)
                .title("SAVE 10%")
                .description("Sign up for our newsletter and SAVE 10% on your order!")
                .category(Category.GAMING)
                .amount(100)
                .price(0)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        Coupon c20 = Coupon.builder()
                .company(company18)
                .title("Best Sellers ")
                .description("Save 15% off with code: LABORDAY")
                .category(Category.GAMING)
                .amount(100)
                .price(0)
                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
                .build();

        couponRepository.saveAll(Arrays.asList(
                c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
                c11, c12, c13, c14, c15, c16, c17, c18, c19, c20
        ));
        TablePrinter.print(couponRepository.findAll());

        System.out.println("   **********          GET ALL COUPONS BY COMPANY ID #1         ********** \n"
                + "\n");
        TablePrinter.print(couponRepository.findByCompanyId(1));

        System.out.println("   **********          CUSTOMERS          ********** \n");

        Customer customer1 = Customer.builder()
                .firstName("Alex")
                .lastName("Berger")
                .email("alex@mail.com")
                .password("customer1234")
//                .coupons(Arrays.asList(c8, c9, c3))
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Anastasia")
                .lastName("Berger")
                .email("anastasia@mail.com")
                .password("customer1234")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("Mishel")
                .lastName("Berger")
                .email("mishel@mail.com")
                .password("customer1234")
                .build();

        Customer customer4 = Customer.builder()
                .firstName("Adam")
                .lastName("Berger")
                .email("adam@mail.com")
                .password("customer1234")
//                .coupons(Arrays.asList(c1, c2, c3))
                .build();

        customerRepository.saveAllAndFlush(Arrays.asList(customer1, customer2, customer3, customer4));
        TablePrinter.print(customerRepository.findAll());

//        System.out.println("   **********          CUSTOMERS SET COUPONS          ********** \n");

    }
}
