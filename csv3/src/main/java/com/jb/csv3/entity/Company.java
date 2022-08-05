package com.jb.csv3.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_id")
    @Singular
    private List<Coupon> coupons = new ArrayList<>();

    public void setId(int id) throws CouponSystemException {
        if (this.id == 0) {
            this.id = id;
        }
        throw new CouponSystemException(ErrMsg.ILLEGAL_ACTION_EXCEPTION);
    }
}