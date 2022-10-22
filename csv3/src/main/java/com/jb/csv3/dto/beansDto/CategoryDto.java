package com.jb.csv3.dto.beansDto;

import com.jb.csv3.beans.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private String name;
    private List<Coupon> coupons = new ArrayList<>();
    private String image;
}
