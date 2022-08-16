//package com.jb.csv3.clr;
//
//import com.jb.csv3.entity.Company;
//import com.jb.csv3.entity.Customer;
//import com.jb.csv3.exeptions.CouponSystemException;
//import com.jb.csv3.login.ClientService;
//import com.jb.csv3.login.LoginManager;
//import com.jb.csv3.entity.Coupon;
//import com.jb.csv3.enums.Category;
//import com.jb.csv3.enums.ClientType;
//import com.jb.csv3.repository.CompanyRepository;
//import com.jb.csv3.repository.CouponRepository;
//import com.jb.csv3.repository.CustomerRepository;
//import com.jb.csv3.service.*;
//import com.jb.csv3.utils.TablePrinter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//@Component
//@Order(2)
//public class ServicesTest implements CommandLineRunner {
//
//    @Autowired
//    private LoginManager loginManager;
//
//    @Autowired
//    private ClientService clientService;
//    @Autowired
//    private AdminServiceImpl adminServiceImpl;
//    @Autowired
//    private CompanyServiceImpl companyServiceImpl;
//    @Autowired
//    private CustomerServiceImpl customerServiceImpl;
//
//    @Autowired
//    private CouponRepository couponRepository;
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("ADMIN SERVICE TEST");
//
//        ClientService adminGoodLogin = null;
//        try {
//            adminGoodLogin = loginManager.login(ClientType.ADMINISTRATOR, "admin@admin.com", "admin");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("ADMIN IS ON THE BOARD : " + adminGoodLogin.getClass());
//
//        AdminServiceImpl adminLogin = (AdminServiceImpl) adminGoodLogin;
//
//        Company newCompanyByAdmin = Company.builder()
//                .email("companyByUser1@mail.com")
//                .password("companyByAdminPass")
//                .name("Company by Admin 1")
//                .coupon(Coupon.builder()
//                        .price(10.5)
//                        .amount(10)
//                        .title("CP by AdminCompany")
//                        .startDate(Date.valueOf(LocalDate.of(2022, 8, 04)))
//                        .endDate(Date.valueOf(LocalDate.of(2022, 8, 30)))
//                        .build())
//                .build();
//        System.out.println("ADD COMPANY BY ADMIN");
//        adminLogin.addCompany(newCompanyByAdmin);
//        TablePrinter.print(companyRepository.findAll());
//
//        System.out.println("UPDATE EMAIL COMPANY BY ADMIN");
//
//        Company companyToUpdate = companyRepository.findById(5).get();
//        companyToUpdate.setEmail("newmail@gmail.com");
//        adminLogin.updateCompany(companyToUpdate);
//        TablePrinter.print(companyRepository.findAll());
//
//        System.out.println("DELETE COMPANY 5 BY ADMIN");
//        adminLogin.deleteCompany(5);
//        TablePrinter.print(companyRepository.findAll());
//
//        System.out.println("GET ALL COMPANIES BY ADMIN");
//        TablePrinter.print(adminLogin.getAllCompanies());
//
//        System.out.println("GET ONE CMP BY ID ADMNSRV");
//        TablePrinter.print(adminLogin.getOneCompany(1));
//
//        System.out.println("ADD CSTMR");
//        adminLogin.addCustomer(
//                Customer.builder()
//                        .firstName("Vasya")
//                        .lastName("Vasiliev")
//                        .email("vasya@yandex.com")
//                        .password("vasya123")
//                        .build());
//        TablePrinter.print(customerRepository.findAll());
//
//        System.out.println("UPDT CSTMR");
//        Customer cstmrForUpdt = customerRepository.findById(3).get();
//        cstmrForUpdt.setFirstName("Vasilisa");
//        adminLogin.updateCustomer(cstmrForUpdt);
//        TablePrinter.print(customerRepository.findAll());
//
//        System.out.println("DELETE CSTMR #3");
//        adminLogin.deleteCustomer(3);
//        TablePrinter.print(customerRepository.findAll());
//
//        System.out.println("GET ALL CSTMRS");
//        TablePrinter.print(adminLogin.getAllCustomers());
//
//        System.out.println("GET ONE CSTMR");
//        TablePrinter.print(adminLogin.getOneCustomer(2));
//
//        System.out.println("COMPANY SERVICE TEST");
//        System.out.println("Company Login with correct details:");
//
//        ClientService companyGoodLogin = null;
//        try {
//            companyGoodLogin = loginManager.login(ClientType.COMPANY, "company1@mail.com", "company1234");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println(companyGoodLogin.getClass());
//        CompanyServiceImpl companyLogin = (CompanyServiceImpl) companyGoodLogin;
//
//        System.out.println("COMPANY COUPONS AFTER LOGIN");
//        TablePrinter.print(companyLogin.getCompanyCoupons());
//
//        companyLogin.addCoupon(
//                Coupon.builder()
//                        .companyId(companyLogin.getCompanyDetails().getId())
//                        .title("Coupon V")
//                        .description("coupon 5 description")
//                        .category(Category.CLOTHING)
//                        .amount(100)
//                        .price(41.50)
//                        .startDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
//                        .endDate(Date.valueOf(LocalDate.of(2022, 9, 30)))
//                        .build()
//        );
//
//        companyLogin.addCoupon(
//                Coupon.builder()
//                        .companyId(companyLogin.getCompanyDetails().getId())
//                        .title("Coupon 45")
//                        .description("coupon description")
//                        .category(Category.CLOTHING)
//                        .amount(100)
//                        .price(50)
//                        .startDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
//                        .endDate(Date.valueOf(LocalDate.of(2022, 9, 30)))
//                        .build()
//        );
//
//
//        System.out.println("CHECK FOR COUPN REPO UPDATE");
//        TablePrinter.print(couponRepository.findAll());
//
//        System.out.println("COMPANY ADD COUPON");
//        TablePrinter.print(companyLogin.getCompanyCoupons());
//
//        System.out.println("CMPN UPDT CPN");
//
//        Coupon cpntu = couponRepository.findById(1).get();
//        cpntu.setTitle("UPDATED TITLE");
//        cpntu.setAmount(25);
//
//        System.out.println(cpntu);
//
//        companyLogin.updateCoupon(cpntu);
//        TablePrinter.print(companyLogin.getCompanyCoupons());
//
//        System.out.println("GET CMPN CPNS");
//        TablePrinter.print(companyLogin.getCompanyCoupons());
//
//        System.out.println("GET CMPN CPNS BY CTGR");
//        TablePrinter.print(companyLogin.getCompanyCoupons(Category.CLOTHING));
//
//        System.out.println("GET CMPN CPNS BY MXPRC");
//        TablePrinter.print(companyLogin.getCompanyCoupons(42));
//
//        System.out.println("GET CMPN DETAILS");
//        TablePrinter.print(companyLogin.getCompanyDetails());
//
//        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////");
//        System.out.println("CUSTOMER SERVICE TEST");
//        System.out.println(" ");
//        System.out.println("Customer Login with correct details:");
//
//        ClientService cstmrGoodLogin = null;
//        try {
//            cstmrGoodLogin = loginManager.login(ClientType.CUSTOMER, "alex@mail.com", "abc123");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        CustomerServiceImpl cstmrLogin = (CustomerServiceImpl) cstmrGoodLogin;
//
//        System.out.println("purchaseCoupon");
//        cstmrLogin.purchaseCoupon(newCompanyByAdmin.getId(), cpntu);
//        TablePrinter.print(customerRepository.findByEmail(cstmrLogin.getCustomerDetails().getEmail()));
//
//        System.out.println("getCustomerCoupons");
//        TablePrinter.print(cstmrLogin.getCustomerCoupons());
//
//        System.out.println("getCustomerCoupons by category ELECTRONICS");
//        TablePrinter.print(cstmrLogin.getCustomerCoupons(Category.ELECTRONICS));
//        System.out.println("getCustomerCoupons by category CLOTHING");
//        TablePrinter.print(cstmrLogin.getCustomerCoupons(Category.CLOTHING));
//
//        System.out.println("getCustomerCoupons by maxPrice");
//        TablePrinter.print(cstmrLogin.getCustomerCoupons(11));
//
//        System.out.println("getCustomerDetails");
//        TablePrinter.print(cstmrLogin.getCustomerDetails());
//
//    }
//}
