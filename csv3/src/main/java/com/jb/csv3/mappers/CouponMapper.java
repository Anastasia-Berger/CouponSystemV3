package com.jb.csv3.mappers;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.dto.beansDto.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CouponMapper implements Mapper<Coupon, CouponDto> {

    private final DateMapper dateMapper;

    @Override
    public Coupon toDAO(CouponDto couponDto) {
        return Coupon.builder()
                .company(couponDto.getCompany())
                .category(couponDto.getCategory())
                .title(couponDto.getTitle())
                .description(couponDto.getDescription())
                .startDate(couponDto.getStartDate())
                .endDate(couponDto.getEndDate())
                .amount(couponDto.getAmount())
                .price(couponDto.getPrice())
                .image(couponDto.getImage())
                .build();
    }

    @Override
    public CouponDto toDTO(Coupon coupon) {
        return CouponDto.builder()
                .company(coupon.getCompany())
                .category(coupon.getCategory())
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .amount(coupon.getAmount())
                .price(coupon.getPrice())
                .image(coupon.getImage())
                .build();
    }

    @Override
    public List<Coupon> toDaoList(List<CouponDto> couponDtos) {
        return couponDtos.stream().map(this::toDAO).collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> toDtoList(List<Coupon> coupons) {
        return coupons.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
