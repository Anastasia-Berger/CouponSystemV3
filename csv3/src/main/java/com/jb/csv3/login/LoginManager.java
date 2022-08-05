package com.jb.csv3.login;

import com.jb.csv3.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LoginManager {

    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    public ClientService login(ClientType clientType, String email, String password) throws CouponSystemException {
        switch (clientType) {

            case ADMINISTRATOR:

                if (adminServiceImpl.login(email, password)) {
                    return adminServiceImpl;
                }
                break;

            case COMPANY:
                if (companyServiceImpl.login(email, password)) {
                    return companyServiceImpl;
                }
                break;

            case CUSTOMER:
                if (customerServiceImpl.login(email, password)) {
                    return customerServiceImpl;
                }
                break;

            default:
                throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        return null;
    }
}