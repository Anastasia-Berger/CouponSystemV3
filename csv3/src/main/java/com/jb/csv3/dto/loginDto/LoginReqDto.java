package com.jb.csv3.dto.loginDto;

import com.jb.csv3.beans.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
public class LoginReqDto {

    private String email;
    private String password;
//    @Enumerated(EnumType.STRING)
    private ClientType clientType;

}