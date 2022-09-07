package com.jb.csv3.mappers;

import com.jb.csv3.beans.Company;
import com.jb.csv3.dto.beansDto.CompanyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper implements  Mapper<Company, CompanyDto>{

    @Override
    public Company toDAO(CompanyDto companyDto) {
        return Company.builder()
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword())
                .image(companyDto.getImage())
                .coupons(companyDto.getCoupons())
                .build();
    }

    @Override
    public CompanyDto toDTO(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .email(company.getEmail())
                .password(company.getPassword())
                .image(company.getImage())
                .coupons(company.getCoupons())
                .build();
    }

    @Override
    public List<Company> toDaoList(List<CompanyDto> companyDtos) {
        return companyDtos.stream().map(this::toDAO).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDto> toDtoList(List<Company> companies) {
        return companies.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
