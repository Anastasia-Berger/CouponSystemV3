package com.jb.csv3.dto.loginDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginReqDto {

    private String email;
    private String password;


}