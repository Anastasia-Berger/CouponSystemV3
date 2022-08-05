package com.jb.csv3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jb.csv3.enums.Category;
import lombok.*;

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;
    private String image;

}
