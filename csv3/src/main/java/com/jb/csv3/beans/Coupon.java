package com.jb.csv3.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Coupon extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

//    @Column(name = "company_id", insertable = false)
//    private int companyId;

//    @Column(name = "customer_id")
//    private int customerId;

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

}
