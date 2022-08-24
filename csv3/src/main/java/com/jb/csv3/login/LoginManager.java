package com.jb.csv3.login;

import com.jb.csv3.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager{

//    No autowired for services, because they would delete each other on each render of this class

    @Autowired
    private ApplicationContext ctx; // The object tree of the app

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {

            case ADMINISTRATOR:
                // The container (ctx - objects tree) gets its information on what object to instantiate, configure or manage
                // by reading configuration metadata (@Annotations) we defined for the application
                AdminService adminService =  ctx.getBean(AdminService.class);
                if (adminService.login(email, password)) {
                    return (ClientService) adminService;
                }
                break;

            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (companyService.login(email, password)) {
                    return (ClientService) companyService;
                }
                break;

            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(email, password)) {
                    return (ClientService) customerService;
                }
                break;

            default:
                throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        return null;
    }

}