package com.pavila.blog.mapper;

import com.pavila.blog.dto.TagDTORequest;
import com.pavila.blog.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagDTORequestToTag implements IMapper<TagDTORequest, Tag>{
    @Override
    public Tag map(TagDTORequest in) {
        return Tag.builder()
                .name(in.getName())
                .articles(in.getArticles())
                .build();
    }
}
