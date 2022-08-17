package com.jb.csv3.login;

import com.jb.csv3.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.service.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class LoginManager {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {

            case ADMINISTRATOR:

                if (clientService.login(email, password)) {
                    return (ClientService) adminService;
                }
                break;

            case COMPANY:
                if (clientService.login(email, password)) {
                    return (ClientService) companyService;
                }
                break;

            case CUSTOMER:
                if (clientService.login(email, password)) {
                    return (ClientService) customerService;
                }
                break;

            default:
                throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        return null;
    }
}