package com.jb.csv3.service;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
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
public class CompanyServiceImpl extends ClientService implements CompanyService {

    // This variable stores the company that loged in
    private Company currentCompany;
    // This variable stores the id of loged company
    private int companyID;


    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!companyRepository.existsByEmail(email)) {
            // Incorrect login exception
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }

        // Create company that loged
        Company company = companyRepository.findByEmail(email);

        if (!company.getPassword().equals(password))
            // Incorrect password exception
            throw new CouponSystemException(ErrMsg.INCORRECT_PASSWORD);

        this.currentCompany = company;
        this.companyID = currentCompany.getId();
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        for (Coupon companyCoupon : currentCompany.getCoupons()) {
            // Coupon title can't be the same as already existing in company repository
            if (companyCoupon.getTitle().equals(coupon.getTitle()))
                throw new CouponSystemException(ErrMsg.DUPLICATE_COUPON_TITLE);
        }

        // Saving coupon as ManyToOne bidirectional will automatically update the company coupon list
        coupon.setCompanyId(currentCompany.getId());

        couponRepository.save(coupon);

        // Updating the plain object to include all new coupons.
        List<Coupon> updatedCouponList = companyRepository.findById(currentCompany.getId()).get().getCoupons();
        currentCompany.setCoupons(updatedCouponList);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(coupon.getId())) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        if (couponRepository.existsByTitle(coupon.getTitle())) {
            throw new CouponSystemException(ErrMsg.DUPLICATE_COUPON_TITLE);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponID) throws CouponSystemException {
        if (!couponRepository.existsById(couponID)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }
        couponRepository.deleteById(couponID);
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return currentCompany.getCoupons();
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        List<Coupon> couponListSortedByCategory = currentCompany.getCoupons().stream()
                .filter(coupon -> category.ordinal() == coupon.getCategory().ordinal())
                .collect(Collectors.toList());
        ;

        return couponListSortedByCategory;
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        List<Coupon> couponListSortedByMaxPrice = currentCompany.getCoupons().stream()
                .filter(coupon -> maxPrice >= coupon.getPrice())
                .collect(Collectors.toList());

        return couponListSortedByMaxPrice;
    }

    @Override
    public Company getCompanyDetails() {
        return currentCompany;
    }
}
