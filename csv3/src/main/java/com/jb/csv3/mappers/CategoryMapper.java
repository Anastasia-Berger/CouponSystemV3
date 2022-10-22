package com.jb.csv3.mappers;

import com.jb.csv3.beans.enums.Category;
import com.jb.csv3.dto.beansDto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    @Override
    public Category toDAO(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto toDTO(Category category) {
        return null;
    }

    @Override
    public List<Category> toDaoList(List<CategoryDto> categoryDtos) {
        return null;
    }

    @Override
    public List<CategoryDto> toDtoList(List<Category> categories) {
        return null;
    }
}
