package com.pavila.blog.mapper;

import com.pavila.blog.dto.CategoryDTORequest;
import com.pavila.blog.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTORequestToCategory implements IMapper<CategoryDTORequest, Category>{
    @Override
    public Category map(CategoryDTORequest in) {
        return Category.builder()
                .name(in.getName())
                .build();
    }
}
