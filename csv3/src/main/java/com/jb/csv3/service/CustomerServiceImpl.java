package com.jb.csv3.service;

import com.jb.csv3.entity.Coupon;
import com.jb.csv3.entity.Customer;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.login.ClientService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private Customer currentCustomer;
    private int customerID;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!customerRepository.existsByEmail(email)) {
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        Customer customer = customerRepository.findByEmail(email);

        if (!customer.getPassword().equals(password))
            throw new CouponSystemException(ErrMsg.INCORRECT_PASSWORD);


        this.currentCustomer = customer;
        this.customerID = currentCustomer.getId();
        return true;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        // Check if coupon is stock
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_IS_OUT_OF_STOCK);
        }

        // Check customer's stock of coupons
        for (Coupon customerCoupon : currentCustomer.getCoupons()) {
            if (customerCoupon.getId() == coupon.getId())
                throw new CouponSystemException(ErrMsg.ALREADY_PURCHASED);
        }

        coupon.setAmount(coupon.getAmount() - 1);
        // Updating coupon amount in repository
        couponRepository.saveAndFlush(coupon);

        Customer customerFromDB = customerRepository.findById(currentCustomer.getId()).get();
        customerFromDB.getCoupons().add(coupon);

        // Updating customer coupon list in repository
        customerRepository.saveAndFlush(customerFromDB);

        // Updating current logged user by data from updated repository
        currentCustomer.setCoupons(customerFromDB.getCoupons());
    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        return currentCustomer.getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        List<Coupon> couponListSortedByCategory = currentCustomer.getCoupons().stream()
                .filter(coupon -> category.ordinal() == coupon.getCategory().ordinal())
                .collect(Collectors.toList());
        ;

        return couponListSortedByCategory;
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        List<Coupon> couponListSortedByMaxPrice = currentCustomer.getCoupons().stream()
                .filter(coupon -> maxPrice >= coupon.getPrice())
                .collect(Collectors.toList());

        return couponListSortedByMaxPrice;
    }

    @Override
    public Customer getCustomerDetails() {
        return currentCustomer;
    }
}
