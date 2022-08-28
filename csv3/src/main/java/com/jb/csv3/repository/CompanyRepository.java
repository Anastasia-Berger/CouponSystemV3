package com.jb.csv3.repository;

import com.jb.csv3.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //    @Query(value = "select... ", nativeQuery = true)
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Company findByEmail(String email);

//    @Query(value = "SELECT coupons_id FROM companies_coupons where company_id = :companyID", nativeQuery = true)
//    List<Coupon> findAllByCompanyId(int companyID);

}
