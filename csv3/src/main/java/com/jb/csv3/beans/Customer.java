package com.jb.csv3.beans;

import com.jb.csv3.enums.ClientType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {

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

    public Customer(String email, String password, ClientType customer) {

    }
}
