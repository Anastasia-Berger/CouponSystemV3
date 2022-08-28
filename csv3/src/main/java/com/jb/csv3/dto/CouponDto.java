package com.jb.csv3.dto;

import com.jb.csv3.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponDto {
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
}
