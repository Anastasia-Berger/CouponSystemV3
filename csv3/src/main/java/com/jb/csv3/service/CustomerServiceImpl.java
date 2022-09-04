package com.jb.csv3.service;

import com.jb.csv3.beans.Coupon;
import com.jb.csv3.beans.Customer;
import com.jb.csv3.dto.beansDto.CouponDto;
import com.jb.csv3.dto.beansDto.CustomerDto;
import com.jb.csv3.enums.Category;
import com.jb.csv3.enums.ClientType;
import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import com.jb.csv3.mappers.CouponMapper;
import com.jb.csv3.mappers.CustomerMapper;
import com.jb.csv3.security.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CouponMapper couponMapper;

    @Override
    public void register(String email, String password) throws CouponSystemException {

        if(customerRepository.existsByEmail(email)){
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXIST);
        }

        Customer customer = new Customer(email,password, ClientType.CUSTOMER);
        customerRepository.save(customer);
    }

    @Override
    public UUID login(String email, String password) throws CouponSystemException {
//        if (!customerRepository.findByEmailAndPassword(email, password))
//            throw new CouponSystemException(ErrMsg.INCORRECT_LOGIN);
//        return false;

        if(!customerRepository.existsByEmailAndPassword(email,password)){
            throw new CouponSystemException(ErrMsg.EMAIL_OR_PASSWORD_INCORRECT);
        }

        Customer customer = customerRepository.findTop1ByEmail(email);
        int customerID = customer.getId();
        ClientType clientType = ClientType.CUSTOMER;

        Information information = new Information(customerID,email,clientType);
        return tokenManager.addToken(information);
    }

    @Override
    public void logout(UUID token) {

    }

    @Override
    public CouponDto purchaseCoupon(int customerID, CouponDto couponDto) throws CouponSystemException {
        Coupon coupon = couponMapper.toDAO(couponDto);

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

        Customer customerFromDB = customerRepository.findById(customerID).get();
        customerFromDB.getCoupons().add(coupon);

        // Updating customer coupon list in repository
        customerRepository.saveAndFlush(customerFromDB);

        // Updating current logged user by data from updated repository
        customerRepository.findById(customerID).get().setCoupons(customerFromDB.getCoupons());

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
    public CustomerDto getCustomerDetails(int customerID) {
        return customerMapper.toDTO(customerRepository.findById(customerID).get());
    }
}
