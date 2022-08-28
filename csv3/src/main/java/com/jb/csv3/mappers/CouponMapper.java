package com.jb.csv3.mappers;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.dto.CouponDto;
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
        return null;
    }

    @Override
    public CouponDto toDTO(Coupon coupon) {
        return null;
    }

    @Override
    public List<Coupon> toDaoList(List<CouponDto> couponDtos) {
        return null;
    }

    @Override
    public List<CouponDto> toDtoList(List<Coupon> coupons) {
        return null;
    }
}
