package com.example.test.mapper;

import com.example.test.db.entity.Category;
import com.example.test.dto.category.res.CategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
public    CategoryResponseDto toDto(Category c){
    CategoryResponseDto res = new CategoryResponseDto();
    res.setPkey(c.getPkey());
    res.setNameEn(c.getNameEn());
    res.setNameTr(c.getNameTr());
    res.setDescriptionEn(c.getDescriptionEn());
    res.setDescriptionTr(c.getDescriptionTr());
    res.setActive(c.getActive());
    res.setOrderIndex( c.getOrderIndex() );
    res.setCreatedAt( c.getCreatedAt() );
    res.setUpdatedAt( c.getUpdatedAt() );
    return res;

}
}
