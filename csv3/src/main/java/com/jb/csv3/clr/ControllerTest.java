package com.jb.csv3.clr;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.controllers.AdminController;
import com.jb.csv3.controllers.CompanyController;
import com.jb.csv3.controllers.CustomerController;
import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.mappers.CompanyMapper;
import com.jb.csv3.mappers.CouponMapper;
import com.jb.csv3.repository.CompanyRepository;
import com.jb.csv3.repository.CouponRepository;
import com.jb.csv3.repository.CustomerRepository;
import com.jb.csv3.security.Information;
import com.jb.csv3.security.LoginManager;
import com.jb.csv3.security.TokenManager;
import com.jb.csv3.service.AdminService;
import com.jb.csv3.service.ClientService;
import com.jb.csv3.service.CompanyService;
import com.jb.csv3.service.CustomerService;
import com.jb.csv3.utils.TablePrinter;
import com.jb.csv3.utils.Titles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Component
@Order(4)
@RequiredArgsConstructor
public class ControllerTest implements CommandLineRunner {


    private final LoginManager loginManager;
    private final TokenManager tokenManager;

    private final AdminController adminController;
    private final CustomerController customerController;
    private final CompanyController companyController;

    private final AdminService adminService;
    private final CustomerService customerService;
    private final CompanyService companyService;

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;

    private final CompanyMapper companyMapper;
    private final CouponMapper couponMapper;

    private static final String URL = "http://localhost:9090/";

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Titles.CONTROLLER_TEST);

//        System.out.println("   **********          ADMIN UUID LOGIN          ********** \n");
//        UUID admin = loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//        System.out.println("ADMIN TOKEN:   >>>   " + admin.toString());
//
//        System.out.println("adminController.countCoupons(admin)   >>>   " + adminController.countCoupons(admin));
//        System.out.println("adminService.countCoupons()   >>>   " + adminService.countCoupons());
//
//        System.out.println("adminService.getOneCompany(companyID)   >>>   " + adminService.getOneCompany(1));
//        System.out.println("adminController.getOneCompany(1, admin)   >>>   " + adminController.getOneCompany(1, admin));


        System.out.println("   **********          COMPANY UUID LOGIN          ********** \n");
//        UUID companyToken = loginManager.login("pizza-hut@mail.com", "company1234", ClientType.COMPANY);
//        System.out.println("COMPANY TOKEN:   >>>   " + companyToken.toString());
//
//        int userID = tokenManager.getUserID(companyToken);
//        companyService.getCompanyDetails(userID);
//
//        Company companyForCoupon = companyRepository.findById(userID).get();
//        System.out.println("Coupons before change");
//        TablePrinter.print(companyService.getCompanyCoupons(userID));
//
//        Coupon newCouponToAdd = Coupon.builder()
//                .company(companyForCoupon)
//                .title("Coupon Test")
//                .description("Coupon from controller test")
//                .category(Category.FOOD)
//                .amount(100)
//                .price(25.9)
//                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
//                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
//                .build();
//
////        System.out.println("Coupon from repo : " + couponRepository.findById(coupon.getId()));
//
//        CouponDto couponDto = couponMapper.toDTO(newCouponToAdd);
//        companyService.addCoupon(userID, couponDto);
//
//        Coupon newCouponToUpdate = Coupon.builder()
//                .company(companyForCoupon)
//                .title("Company Service update")
//                .description("Coupon for update")
//                .category(Category.FOOD)
//                .amount(23)
//                .price(2)
//                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
//                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
//                .build();
//
//        Coupon newCouponToUpdate2 = Coupon.builder()
//                .company(companyForCoupon)
//                .title("Company Controller update")
//                .description("Coupon for update")
//                .category(Category.FOOD)
//                .amount(23)
//                .price(2)
//                .startDate(Date.valueOf(LocalDate.of(2022, 11, 1)))
//                .endDate(Date.valueOf(LocalDate.of(2022, 12, 30)))
//                .build();
//
//        CouponDto couponDtoUpd = couponMapper.toDTO(newCouponToUpdate);
//        CouponDto couponDtoUpd2 = couponMapper.toDTO(newCouponToUpdate2);
//
//        System.out.println("Coupons after service update");
//        companyService.updateCoupon(userID, 5, couponDtoUpd);
//        TablePrinter.print(companyService.getCompanyCoupons(userID));
//
//        System.out.println("Coupons after controller update");
//        companyController.updateCoupon(5, couponDtoUpd2, companyToken);
//        TablePrinter.print(companyService.getCompanyCoupons(userID));


        System.out.println("   **********          CUSTOMER UUID LOGIN          ********** \n");

        UUID customerToken = loginManager.login("adam@mail.com", "customer1234", ClientType.CUSTOMER);
        System.out.println("   CUSTOMER TOKEN:   >>>   " + customerToken.toString());
        System.out.println(" ");

        CouponDto ctb = couponMapper.toDTO(couponRepository.findById(2).get());
        customerController.purchaseCoupon(2, customerToken);

        System.out.println("\n   CUSTOMER COUPONS FROM CONTROLLER   >>>   \n");
        TablePrinter.print(customerController.getCustomerCoupons(customerToken));

        System.out.println("   CUSTOMER DETAILS FROM CONTROLLER   >>>   \n");
        TablePrinter.print(customerController.getCustomerDetails(customerToken));

        System.out.println("   CUSTOMER FROM REPOSITORY   ");
        TablePrinter.print(customerRepository.findById(4).get());
    }
}
