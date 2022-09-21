package com.jb.csv3.service;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.beans.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.mappers.CouponMapper;
import com.jb.csv3.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CouponMapper couponMapper;

    @Override
    public void register(String firstName, String lastName, String email, String password) throws CouponSystemException {

        if (customerRepository.existsByEmail(email)) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }

        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();

        customerRepository.saveAndFlush(customer);
    }

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!customerRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
        }
        return true;

//        Customer customer = customerRepository.findTop1ByEmail(email);
//        int customerID = customer.getId();
//        ClientType clientType = ClientType.CUSTOMER;
//
//        Information information = new Information(customerID, email, clientType);
//        return tokenManager.addToken(information);
    }

    @Override
    public void logout() {

    }

    @Override
    public List<CouponDto> getAllCoupons() {
        return couponMapper.toDtoList(couponRepository.findAll());
    }

    @Override
    public CouponDto purchaseCoupon(int customerID, CouponDto couponDto) throws CouponSystemException {
        Coupon coupon = couponMapper.toDAO(couponDto);

        // Check if coupon is stock
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_IS_OUT_OF_STOCK);
        }

        // Check customer's stock of coupons
        if (couponRepository.existsByCustomerIdAndCouponId(customerID, coupon.getId()) != 0) {
            throw new CouponSystemException(ErrMsg.ALREADY_PURCHASED);
        }


        Customer customerFromDB = customerRepository.findById(customerID).get();

        List<Coupon> coupons = customerFromDB.getCoupons();
        coupons.add(coupon);
        // Updating current logged user by data from updated repository
        customerFromDB.setCoupons(coupons);
        // Updating customer coupon list in repository
        customerRepository.saveAndFlush(customerFromDB);

        // Updating current
        coupon.setAmount(coupon.getAmount() - 1);
        // Updating coupon amount in repository
        return couponMapper.toDTO(couponRepository.saveAndFlush(coupon));
    }

    @Override
    public List<CouponDto> getCustomerCoupons(int customerID) {
        return couponMapper.toDtoList(customerRepository.findById(customerID).get().getCoupons());
    }

    @Override
    public List<CouponDto> getCustomerCoupons(int customerID, Category category) {
        List<Coupon> couponListSortedByCategory = customerRepository.findById(customerID).get().getCoupons().stream()
                .filter(coupon -> category.ordinal() == coupon.getCategory().ordinal())
                .collect(Collectors.toList());
        ;

        return couponMapper.toDtoList(couponListSortedByCategory);
    }

    @Override
    public List<CouponDto> getCustomerCoupons(int customerID, double maxPrice) {
        List<Coupon> couponListSortedByMaxPrice = customerRepository.findById(customerID).get().getCoupons().stream()
                .filter(coupon -> maxPrice >= coupon.getPrice())
                .collect(Collectors.toList());

        return couponMapper.toDtoList(couponListSortedByMaxPrice);
    }

    @Override
    public CustomerDto getCustomerDetails(int customerID) throws CouponSystemException {
        if (!customerRepository.existsById(customerID)){
            throw new CouponSystemException(ErrMsg.ID_NOT_EXIST);
        }

        Customer customer = customerRepository.findById(customerID).get();
        return customerMapper.toDTO(customer);
    }

    @Override
    public int count() {
        return (int) customerRepository.count();
    }
}
