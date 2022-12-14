package com.jb.csv3.dto.beansDto;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponDto {

    private int id;
    private Company company;

    private Category category;
    private String title;
    private String description;
//    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date startDate;
//    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date endDate;
    private int amount;
    private double price;
    private String image;
}
