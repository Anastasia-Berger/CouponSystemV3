package com.jb.csv3.security;

import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginManager {

    private final AdminServiceImpl adminService;
    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;
    private final TokenManager tokenManager;

    @Autowired
    private ApplicationContext ctx;

    public UUID login(String email, String password, ClientType clientType) throws CouponSystemException {

        switch (clientType) {

            case ADMINISTRATOR:
                if (adminService.login(email, password)) {//If wrong, exception thrown
                    Information information = new Information(email, clientType);
                    UUID token = tokenManager.addToken(information);
                    return token;
                }
                break;

            case COMPANY:
                if (companyService.login(email, password)) {//If wrong, exception thrown
                    Information information = new Information(email, clientType);
                    UUID token = tokenManager.addToken(information);
                    return token;
                }
                break;

            case CUSTOMER:
                if (customerService.login(email, password)) {//If wrong, exception thrown;
                    Information information = new Information(email, clientType);
                    UUID token = tokenManager.addToken(information);
                    return token;
                }
                break;

            default:
                new CouponSystemException(ErrMsg.AUTH);
        }
        return null;
    }

    public ClientService simpleLogin(String email, String password, ClientType clientType) throws CouponSystemException {

        switch (clientType) {

            case ADMINISTRATOR:
                // The container (ctx - objects tree) gets its information on what object to instantiate, configure or manage
                // by reading configuration metadata (@Annotations) we defined for the application

                AdminService adminService =  ctx.getBean(AdminService.class);
                if (adminService.login(email, password))
                    return (ClientService) adminService;
                break;

            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (companyService.login(email, password))
                    return (ClientService) companyService;
                break;

            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(email, password))
                    return (ClientService) customerService;
                break;

            default:
                throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        return null;
    }
}