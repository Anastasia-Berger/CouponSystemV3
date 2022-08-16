package com.jb.csv3.service;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
import com.jb.csv3.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.login.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!companyRepository.existsByEmail(email)) {
            // Incorrect login exception
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        // Creating copy of company that logged
        Company company = companyRepository.findByEmail(email);

        if (!company.getPassword().equals(password))
            // Incorrect password exception
            throw new CouponSystemException(ErrMsg.INCORRECT_PASSWORD);

        return true;
    }

    @Override
    public void addCoupon(int companyID, Coupon coupon) throws CouponSystemException {

        for (Coupon companyCoupon : companyRepository.findById(companyID).get().getCoupons()) {
            if (companyCoupon.getTitle().equals(coupon.getTitle()))
                // Coupon title can't be the same as already existing in company repository
                throw new CouponSystemException(ErrMsg.DUPLICATE_COUPON_TITLE);
        }

        // Associates coupon with the owner company
        coupon.setCompanyId(companyID);
        // Adding the new coupon to repository
        couponRepository.save(coupon);

        // Updating the plain object to include all new coupons.
        List<Coupon> updatedCouponList = companyRepository.findById(companyID).get().getCoupons();
        companyRepository.findById(companyID).get().setCoupons(updatedCouponList);
    }

    @Override
    public void updateCoupon(int companyID, Coupon coupon) throws CouponSystemException {
        // Checks if the coupon is in the repo
        if (!couponRepository.existsById(coupon.getId())) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }

        // Checks if company is the owner of the coupon
        if (coupon.getCompanyId() != companyID) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED_EVENT);
        }

        // Checks the unique title of the coupon
        for (Coupon companyCoupon : companyRepository.findById(companyID).get().getCoupons()) {
            if (companyCoupon.getTitle().equals(coupon.getTitle()))
                // Coupon title can't be the same as already existing in company repository
                throw new CouponSystemException(ErrMsg.DUPLICATE_COUPON_TITLE);
        }

        // Updating the coupon
        couponRepository.saveAndFlush(coupon);

        List<Coupon> updatedCouponList = companyRepository.findById(companyID).get().getCoupons();
        companyRepository.findById(companyID).get().setCoupons(updatedCouponList);
    }

    @Override
    public void deleteCoupon(int companyID, int couponID) throws CouponSystemException {
        // Checks if the coupon is in the repo
        if (!couponRepository.existsById(couponID)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }

        couponRepository.deleteCouponPurchaseHistory(couponID);
        couponRepository.deleteById(couponID);

        List<Coupon> updatedCouponList = companyRepository.findById(companyID).get().getCoupons();
        companyRepository.findById(companyID).get().setCoupons(updatedCouponList);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyID) {
        return companyRepository.findById(companyID).get().getCoupons();
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyID, Category category) {
        List<Coupon> couponListSortedByCategory = companyRepository.findById(companyID).get().getCoupons().stream()
                .filter(coupon -> category.ordinal() == coupon.getCategory().ordinal())
                .collect(Collectors.toList());

        return couponListSortedByCategory;
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyID, double maxPrice) {
        List<Coupon> couponListSortedByMaxPrice = companyRepository.findById(companyID).get().getCoupons().stream()
                .filter(coupon -> maxPrice >= coupon.getPrice())
                .collect(Collectors.toList());

        return couponListSortedByMaxPrice;
    }

    @Override
    public Company getCompanyDetails(int companyID) {
        return companyRepository.findById(companyID).get();
    }
}
