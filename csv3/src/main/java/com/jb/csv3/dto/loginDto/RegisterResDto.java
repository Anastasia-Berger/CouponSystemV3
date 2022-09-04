package com.jb.csv3.dto.loginDto;

import com.jb.csv3.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterResDto {

    private String email;
    private UUID token;
    private ClientType clientType;
}
