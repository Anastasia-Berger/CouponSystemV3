package com.jb.csv3.mappers;

import com.jb.csv3.beans.Customer;
import com.jb.csv3.dto.beansDto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public Customer toDAO(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .image(customerDto.getImage())
                .build();
    }

    @Override
    public CustomerDto toDTO(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .image(customer.getImage())
                .build();
    }

    @Override
    public List<Customer> toDaoList(List<CustomerDto> customerDtos) {
        return customerDtos.stream().map(this::toDAO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customer> customers) {
        return customers.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
