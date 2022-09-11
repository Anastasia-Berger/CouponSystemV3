package com.jb.csv3.beans;

import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Company extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 40, updatable = false)
    private String name;

    @Column(unique = true, length = 40)
    private String email;

    @Column(nullable = false)
    private String password;

    @ToString.Exclude
    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "company")
//    @JoinColumn(name = "company_id")
    @Singular
    private List<Coupon> coupons = new ArrayList<>();

    private String image;

    public void setId(int id) throws CouponSystemException {
        if (this.id == 0) {
            this.id = id;
        }
        throw new CouponSystemException(ErrMsg.ILLEGAL_ACTION_EXCEPTION);
    }
}