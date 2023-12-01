package com.pavila.blog.mapper;

import com.pavila.blog.dto.ArticleDTORequest;
import com.pavila.blog.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleDTORequestToArticle implements IMapper<ArticleDTORequest, Article>{

    @Override
    public Article map(ArticleDTORequest in) {

        return Article.builder()
                .title(in.getTitle())
                .content(in.getContent())
                .category(in.getCategory())
                .tags(in.getTags())
                .comments(in.getComments())
                .build();
    }
}
