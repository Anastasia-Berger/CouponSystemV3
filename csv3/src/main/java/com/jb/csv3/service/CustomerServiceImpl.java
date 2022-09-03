package com.jb.csv3.service;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        return customerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void purchaseCoupon(int customerID, Coupon coupon) throws CouponSystemException {
        // Check if coupon is stock
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_IS_OUT_OF_STOCK);
        }

        // Check customer's stock of coupons
        for (Coupon customerCoupon : customerRepository.findById(customerID).get().getCoupons()) {
            if (customerCoupon.getId() == coupon.getId())
                throw new CouponSystemException(ErrMsg.ALREADY_PURCHASED);
        }

        coupon.setAmount(coupon.getAmount() - 1);
        // Updating coupon amount in repository
        couponRepository.saveAndFlush(coupon);

        Customer customerFromDB = customerRepository.findById(customerID).get();
        customerFromDB.getCoupons().add(coupon);

        // Updating customer coupon list in repository
        customerRepository.saveAndFlush(customerFromDB);

        // Updating current logged user by data from updated repository
        customerRepository.findById(customerID).get().setCoupons(customerFromDB.getCoupons());
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID) {
        return customerRepository.findById(customerID).get().getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID, Category category) {
        List<Coupon> couponListSortedByCategory = customerRepository.findById(customerID).get().getCoupons().stream()
                .filter(coupon -> category.ordinal() == coupon.getCategory().ordinal())
                .collect(Collectors.toList());
        ;

        return couponListSortedByCategory;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerID, double maxPrice) {
        List<Coupon> couponListSortedByMaxPrice = customerRepository.findById(customerID).get().getCoupons().stream()
                .filter(coupon -> maxPrice >= coupon.getPrice())
                .collect(Collectors.toList());

        return couponListSortedByMaxPrice;
    }

    @Override
    public Customer getCustomerDetails(int customerID) {
        return customerRepository.findById(customerID).get();
    }
}
