package com.jb.csv3.security;

import com.jb.csv3.beans.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {

    private int id;
    private String email;
    private ClientType clientType;
    private LocalDateTime time = LocalDateTime.now();

    public Information(int id, String email, ClientType clientType) {
        this.id = id;
        this.email = email;
        this.clientType = clientType;
    }

    public Information(String email, ClientType clientType) {
        this.email = email;
        this.clientType = clientType;
    }
}
