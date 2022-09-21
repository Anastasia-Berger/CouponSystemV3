package com.jb.csv3.service;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import com.jb.csv3.dto.beansDto.CompanyDto;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.mappers.CompanyMapper;
import com.jb.csv3.mappers.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CouponMapper couponMapper;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!companyRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }
        return true;
    }

    @Override
    public void logout() {

    }

//    @Override
//    public CompanyDto updateCompany(int companyID, CompanyDto companyDto) throws CouponSystemException {
//
//        Company company = companyMapper.toDAO(companyDto);
//
//        // Checking if the company is exists for update
//        if (!companyRepository.existsById(companyID)) {
//            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
//        }
//        return companyMapper.toDTO(companyRepository.saveAndFlush(company));
//    }

    @Override
    public CouponDto addCoupon(int companyID, CouponDto couponDto) throws CouponSystemException {

        // Checks the unique title of the coupon
        List<Coupon> companyCoupons = companyRepository.findById(companyID).get().getCoupons();
        if (companyCoupons.contains(couponDto.getTitle()))
            // Coupon title can't be the same as already existing in company repository
            throw new CouponSystemException(ErrMsg.DUPLICATE_COUPON_TITLE);

        // Associates coupon with the owner company
        Coupon coupon = couponMapper.toDAO(couponDto);
        Company company = companyRepository.findById(companyID).get();
        coupon.setCompany(company);

        // Updating the plain object to include all new coupons.
        List<Coupon> updatedCouponList = companyRepository.findById(companyID).get().getCoupons();
        companyRepository.findById(companyID).get().setCoupons(updatedCouponList);

        return couponMapper.toDTO(couponRepository.save(coupon));
    }

    @Override
    public CouponDto updateCoupon(int companyID, int couponId, CouponDto couponDto) throws CouponSystemException {
        // Checks if the coupon is in the repo
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }

        couponDto.setId(couponId);
        Coupon coupon = couponMapper.toDAO(couponDto);

        // Checks if company is the owner of the coupon
        if (coupon.getCompany().getId() != companyID) {
            throw new CouponSystemException(ErrMsg.UNAUTHORIZED_EVENT);
        }

        // Checks the unique title of the coupon
        List<Coupon> companyCoupons = companyRepository.findById(companyID).get().getCoupons();
        if (companyCoupons.contains(coupon.getTitle()))
            // Coupon title can't be the same as already existing in company repository
            throw new CouponSystemException(ErrMsg.DUPLICATE_COUPON_TITLE);

        List<Coupon> updatedCouponList = companyRepository.findById(companyID).get().getCoupons();
        companyRepository.findById(companyID).get().setCoupons(updatedCouponList);

        // Updating the coupon in repo
        return couponMapper.toDTO(couponRepository.saveAndFlush(coupon));
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
    public List<CouponDto> getCompanyCoupons(int companyID) {
        return couponMapper.toDtoList(companyRepository.findById(companyID).get().getCoupons());
    }

    @Override
    public List<CouponDto> getCompanyCoupons(int companyID, Category category) {
        List<Coupon> couponListSortedByCategory = companyRepository.findById(companyID).get().getCoupons().stream()
                .filter(coupon -> category.ordinal() == coupon.getCategory().ordinal())
                .collect(Collectors.toList());

        return couponMapper.toDtoList(couponListSortedByCategory);
    }

    @Override
    public List<CouponDto> getCompanyCoupons(int companyID, double maxPrice) {
        List<Coupon> couponListSortedByMaxPrice = companyRepository.findById(companyID).get().getCoupons().stream()
                .filter(coupon -> maxPrice >= coupon.getPrice())
                .collect(Collectors.toList());

        return couponMapper.toDtoList(couponListSortedByMaxPrice);
    }

    @Override
    public CompanyDto getCompanyDetails(int companyID) {
        return companyMapper.toDTO(companyRepository.findById(companyID).get());
    }

    @Override
    public int count() {
        return (int) companyRepository.count();
    }

}
