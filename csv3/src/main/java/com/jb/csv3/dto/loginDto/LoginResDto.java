package com.jb.csv3.dto.loginDto;

import com.jb.csv3.beans.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class LoginResDto {  // what going back to the outside

    private String email;
    private UUID token;
    private ClientType clientType;
}
