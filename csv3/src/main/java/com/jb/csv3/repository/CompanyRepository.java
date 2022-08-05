package com.jb.csv3.repository;

import com.jb.csv3.entity.Company;
import com.jb.csv3.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //    @Query(value = "select... ", nativeQuery = true)
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Company findByEmail(String email);

//    @Query(value = "SELECT coupons_id FROM companies_coupons where company_id = :companyID", nativeQuery = true)
//    List<Coupon> findAllByCompanyId(int companyID);

}
