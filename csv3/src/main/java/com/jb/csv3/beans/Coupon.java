package com.jb.csv3.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    private Company company;

    @Column(name = "company_id")
    private int companyId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false, length = 50)
    private String title;

    private String description;

    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date startDate;
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date endDate;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;
    private String image;

    public void setId(int id) throws CouponSystemException {
        if (this.id == 0) {
            this.id = id;
        }
        throw new CouponSystemException(ErrMsg.ILLEGAL_ACTION_EXCEPTION);
    }

}
