package com.jb.csv3.dto.beansDto;

import com.jb.csv3.beans.Coupon;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private List<Coupon> coupons = new ArrayList<>();
    private String image;

}
