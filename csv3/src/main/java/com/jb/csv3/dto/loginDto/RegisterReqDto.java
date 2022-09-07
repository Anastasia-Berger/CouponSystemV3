package com.jb.csv3.dto.loginDto;

import com.jb.csv3.beans.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterReqDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
