package com.jb.csv3.dto;

import com.jb.csv3.beans.Coupon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Coupon> coupons = new ArrayList<>();

    private String image;
}
