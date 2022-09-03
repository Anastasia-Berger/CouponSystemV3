package com.jb.csv3.dto;

import com.jb.csv3.beans.Coupon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
