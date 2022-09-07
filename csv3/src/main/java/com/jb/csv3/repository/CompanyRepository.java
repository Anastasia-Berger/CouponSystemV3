package com.jb.csv3.repository;

import com.jb.csv3.beans.Company;
import com.jb.csv3.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //    @Query(value = "select... ", nativeQuery = true)
    boolean existsByName(String name);

    boolean existsByEmail(String email);
    Company findTop1ByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    boolean findByEmailAndPassword(String email, String password);



}
