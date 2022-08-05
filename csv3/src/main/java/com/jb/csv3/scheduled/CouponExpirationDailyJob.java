package com.jb.csv3.scheduled;

import com.jb.csv3.entity.Coupon;
import com.jb.csv3.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class CouponExpirationDailyJob implements Runnable {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    // At 01:00 everyday
    @Scheduled(cron = "0 0 1 * * *")
    public void run() {
        for (Coupon coupon : couponRepository.findAll()) {
            if (Date.valueOf(LocalDate.now()).after(coupon.getEndDate())) {
                couponRepository.delete(coupon);
            }
        }
    }
}