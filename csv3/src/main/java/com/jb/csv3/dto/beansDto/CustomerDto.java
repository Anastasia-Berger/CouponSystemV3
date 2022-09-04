package com.jb.csv3.dto.beansDto;

import com.jb.csv3.beans.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Coupon> coupons = new ArrayList<>();

    private String image;
}
